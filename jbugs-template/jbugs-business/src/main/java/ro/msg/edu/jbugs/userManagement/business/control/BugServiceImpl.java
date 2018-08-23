package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.converter.BugConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDao;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDaoImpl;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class BugServiceImpl implements BugService{
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @EJB
    private BugDao bugDao;


    private BugConverter bugConverter = new BugConverter();

    @Override
    public BugDto createBug(BugDto bugDto) throws BusinessException {
        log.info("createBug: bugDto={}", bugDto);
        Bug bug = bugConverter.convertDtoToEntity(bugDto);
        log.info("Test: bug={}", bug);
        Optional<Bug> bug1 = bugDao.addBug(bug);
        log.info("Test: bug1={}", bug1);
        return bugDto;
    }

    @Override
    public boolean closeBug(Long bugId) throws BusinessException {
        log.info("bug to close: bugId={}", bugId);
        Optional<Bug> bugOptional = bugDao.closeBug(bugId);
        log.info("Test2: bugOptional={}", bugOptional);
        if (bugOptional.isPresent()) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean changeBugStatus(BugDto bugDto) throws BusinessException {
        log.info("bug status to change: bugId={} newStatus={}", bugDto.getId(), bugDto.getBugStatusType());
        Optional<Bug> bugOptional = bugDao.changeBugStatus(bugDto.getId(), bugDto.getBugStatusType());
        log.info("bug optional fromm persistence: bugOptional={}", bugOptional);
        if (bugOptional.isPresent()) {
            log.info("return true");
            return true;
        }
        log.info("return false");
        return false;
    }
}
