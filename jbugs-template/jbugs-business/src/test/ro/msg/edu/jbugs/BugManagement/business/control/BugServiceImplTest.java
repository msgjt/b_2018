package ro.msg.edu.jbugs.BugManagement.business.control;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.userManagement.business.control.BugServiceImpl;
import ro.msg.edu.jbugs.userManagement.business.control.UserServiceImpl;
import ro.msg.edu.jbugs.userManagement.business.converter.BugConverter;
import ro.msg.edu.jbugs.userManagement.business.converter.UserConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDao;
import ro.msg.edu.jbugs.userManagement.persistence.dao.UserDao;
import ro.msg.edu.jbugs.userManagement.persistence.dao.UserDaoImpl;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class BugServiceImplTest{


    @Mock
    private BugServiceImpl bugService;

    @Mock
    private BugDao bugDao;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    UserConverter userConverter;

    @InjectMocks
    BugConverter bugConverter;

    @InjectMocks
    private UserDto userDto;

    @InjectMocks
    private UserDaoImpl userDao;

    @Test
    public void createBugTest() throws BusinessException {

        BugDto bugDto = new BugDto();
        bugDto.setId(1l);
        bugDto.setTitle("titlu1");
        bugDto.setDescription("descript");
        bugDto.setVersion("1.0");
        bugDto.setFixedInVersion("1.0");
        bugDto.setSeverityType(SeverityType.HIGH);
        bugDto.setBugStatusType(BugStatusType.OPEN);

        BugDto bugDto1 =bugService.createBug(bugDto);
        //assertEquals(bugDto,bugDto1);
    }
}