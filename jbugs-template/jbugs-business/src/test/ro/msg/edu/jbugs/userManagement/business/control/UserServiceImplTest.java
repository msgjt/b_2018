package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.converter.UserConverter;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.dao.UserDao;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.utils.Encryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void generateFirstAttemptUsername_expected_macarc() {
        String username = userService.generateFirstAttemptUsername("Cristian", "Macarie");
        assertEquals("expected macarc but found: " + username, "macarc", username);
    }

    @Test
    public void generateFirstAttemptUsername_expected_galcri() {
        String username = userService.generateFirstAttemptUsername("Cristian", "Gal");
        assertEquals("expected galcri but found: " + username, "galcri", username);
    }

    @Test
    public void generateFirstAttemptUsername_expected_galc00() {
        String username = userService.generateFirstAttemptUsername("C", "Gal");
        assertEquals("expected galc00 but found: " + username, "galc00", username);
    }

    @Test
    public void generateRealUsername_expected_contains_baa000() {
        when(userDao.getUserByUsername(any(String.class)))
                .thenReturn(Optional.of(User.builder().build()))
                .thenReturn(Optional.empty());
        String username = null;
        try {
            username = userService.generateRealUsername("A", "Ba");
            assertTrue(username.contains("baa000"));
        } catch (BusinessException e) {
            fail("Should not reach this point!");
        }

    }

    @Test
    public void generateRealUsername_expected_baa000() {
        when(userDao.getUserByUsername(any(String.class)))
                .thenReturn(Optional.empty());
        String username = null;
        try {
            username = userService.generateRealUsername("A", "Ba");
            assertEquals("baa000", username);
        } catch (BusinessException e) {
            fail("Should not reach this point!");
        }

    }

    @Test
    public void testLogin_Success() {
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("salut");
        when(user.getPassword()).thenReturn(Encryptor.encrypt("secret"));

        when(userDao.getUserByUsername("salut"))
                .thenReturn(Optional.of(user));
        when(userConverter.convertEntityToDto(any(User.class)))
                .thenReturn(UserDto.builder().username("salut").build());

        try {
            UserDto userDTO = userService.login("salut", "secret");
            assertEquals(userDTO.getUsername(), user.getUsername());
        } catch (BusinessException e) {
            fail("Shouldn't reach this point");
        }
    }

    @Test
    public void testCreateUser_Success() {
        UserDto user1 = UserDto.builder()
                .email("aaaa@msggroup.com")
                .firstName("Al")
                .lastName("Lu")
                .password("password")
                .mobileNumber("074019100")
                .build();
        try {
            UserDto userDto = userService.createUser(user1);
            assertEquals(user1.getEmail(), userDto.getEmail());
        } catch (BusinessException e) {
            fail("Shouldn't reach this point");
        }
    }


}