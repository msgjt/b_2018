package ro.msg.edu.jbugs.userManagement.business.control;

import com.sun.xml.internal.bind.v2.TODO;
import ro.msg.edu.jbugs.userManagement.business.converter.BugConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDao;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDaoImpl;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;

@Stateless
public class BugServiceImpl implements BugService{
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private BugDao bugDao = new BugDaoImpl();


    private BugConverter bugConverter = new BugConverter();

    @Override
    public BugDto createBug(BugDto bugDto) throws BusinessException {
        log.info("createBug: bugDto={}", bugDto);
        Bug bug = bugConverter.convertDtoToEntity(bugDto);
        //TODO de verificat addaugarea
        Optional<Bug> bug1 = bugDao.addBug(bug);
        BugDto bugDto1 = bug1.map(bugConverter::convertEntityToDto).orElse(BugDto.builder().build());
        return bugDto;
    }
}
