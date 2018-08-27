package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.converter.BugConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDao;
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

    @Inject
    private BugConverter bugConverter;

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
        return bugOptional.isPresent();
    }

    @Override
    public boolean changeBugStatus(BugDto bugDto) throws BusinessException {
        log.info("bug status to change: bugId={} newStatus={}", bugDto.getId(), bugDto.getBugStatusType());
        Optional<Bug> bugOptional = bugDao.changeBugStatus(bugDto.getId(), bugDto.getBugStatusType());
        log.info("bug optional fromm persistence: bugOptional={}", bugOptional);
        return bugOptional.isPresent();
    }

    @Override
    public BugDto updateBug(BugDto bugDto) throws BusinessException {
        log.info("updateBug from client: bugDto={}", bugDto);
        Bug bug = bugConverter.convertDtoToEntity(bugDto);
        //BugValidator.validateBugForUpdate(bug);
        log.info("BugDTO to Bug: bug={}", bug);
        Optional<Bug> bugOptional = bugDao.updateBug(bug);
        Bug bugResult = bugOptional
                .orElseThrow(() -> new BusinessException(BusinessExceptionCode.CAN_NOT_UPDATE_BUG));
        log.info("bug optional from persistence: bugOptional={}", bugOptional);
        return bugConverter.convertEntityToDto(bugResult);
    }
}
