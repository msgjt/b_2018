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
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.SeverityType;

import javax.inject.Inject;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class BugServiceImplTest{


    @InjectMocks
    private BugServiceImpl bugService;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    UserConverter userConverter;

    @InjectMocks
    private UserDto userDto;

    @Test
    public void createBugTest() throws BusinessException {
        //UserDto user= userService.createUser(userDto);
        BugDto bugDto = new BugDto();
        bugDto.setId(1l);
        bugDto.setTitle("titlu1");
        bugDto.setDescription("descript");
        bugDto.setVersion("1.0");
        bugDto.setFixedInVersion("1.0");
        bugDto.setSeverityType(SeverityType.HIGH);
        bugDto.setBugStatusType(BugStatusType.IN_PROGRESS);
        //bugDto.setCreator(userConverter.convertDtoToEntity(user));
        //bugDto.setAssignee(userConverter.convertDtoToEntity(user));
        assertEquals(bugDto,bugService.createBug(bugDto));

    }
}