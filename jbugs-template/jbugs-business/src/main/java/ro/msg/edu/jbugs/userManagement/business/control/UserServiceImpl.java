package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.converter.UserConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.TokenDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.utils.JwtManager;
import ro.msg.edu.jbugs.userManagement.business.utils.UtilBean;
import ro.msg.edu.jbugs.userManagement.business.validator.UserValidator;
import ro.msg.edu.jbugs.userManagement.persistence.dao.RoleDAO;
import ro.msg.edu.jbugs.userManagement.persistence.dao.RoleDAOImpl;
import ro.msg.edu.jbugs.userManagement.persistence.dao.UserDao;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.*;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDto;
import ro.msg.edu.jbugs.userManagement.business.utils.Encryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Stream;

@Stateless
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private static final Integer MAX_LAST_NAME_LENGTH = 5;
    private static final Integer MIN_USERNAME_LENGTH = 6;
    private static final Integer MIN_RANDOM_PREFIX_RANGE = 0;
    private static final Integer MAX_RANDOM_PREFIX_RANGE = 100;
    private static final Integer INFINITE_LOOP_PREVENTION_TIME = 5 * 1000;

    private Random generator = new Random();

    @EJB
    private UserDao userDao;

    @EJB
    private RoleDAO roleDAO;

    @EJB
    private UtilBean utilBean;

    @Inject
    private UserConverter userConverter;

    @Override
    public UserDto createUser(UserDto userDto) throws BusinessException {
        log.info("createUser: userDto={}", userDto);
        User user = userConverter.convertDtoToEntity(userDto);

        normalizeUser(user);
        UserValidator.validateUser(user);
        HashSet<RoleType> roles = UserValidator.validateRoles(userDto.getRoles());
        if (roles.size() == 0)
            throw new BusinessException(BusinessExceptionCode.ROLES_NOT_VALID);

//        if (userDao.getUserWithEmail(user.getEmail()).isPresent()) {
//            throw new BusinessException(BusinessExceptionCode.EMAIL_EXISTS_ALREADY);
//        }

        HashSet<Role> roleEntities = roleDAO.getRolesByType(roles);

        user.setRoles(roleEntities);
        user.setUsername(generateRealUsername(user.getFirstName(), user.getLastName()));
        user.setPassword(Encryptor.encrypt(userDto.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        Optional<User> user1 = userDao.addUser(user);
        if (user1.isPresent()) {
            log.info("createUser: adding user ro roles={}", roleEntities);
            roleDAO.addUser(user1.get(), roleEntities);
        }
        UserDto userDto1 = user1.map(userConverter::convertEntityToDto).orElse(UserDto.builder().build());
        log.info("createUser: result={}", userDto1);

        return userDto1;
    }

    private void normalizeUser(User user) {
        user.setFirstName(user.getFirstName().trim());
        user.setLastName(user.getLastName().trim());
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("getAllUsers: --- entered");
        List<UserDto> users = new ArrayList<>(userConverter.convertEntitiesToDtos(userDao.getAllUsers()));
        log.info("getAllUsers: result={}", users);
        return users;
    }

    @Override
    public TokenDto login(String username, String password) throws BusinessException {
        log.info("login: username={}", username);
        Optional<User> user = userDao.getUserByUsernameWithRolesAndPermissions(username);
        User user1 = user.orElseThrow(() -> new BusinessException(BusinessExceptionCode.CAN_NOT_GET_USER));
        validateUserForLogin(user1, password);
        TokenDto tokenDto = TokenDto.builder()
                .token(JwtManager.getInstance().createToken(user1))
                .username(user1.getUsername())
                .build();
        tokenDto.setId(user1.getId());
        log.info("login: token={}", tokenDto);
        return tokenDto;
    }

    private void validateUserForLogin(User user, String password) throws BusinessException {
        if(user.getStatus().equals(UserStatus.DEACTIVATED)){
            throw new BusinessException(BusinessExceptionCode.USER_DEACTIVATED);
        }
        if(utilBean.addTry(user.getUsername())){
            userDao.setUserStatusByUsername(user.getUsername(), UserStatus.DEACTIVATED);
            throw new BusinessException(BusinessExceptionCode.USER_DEACTIVATED);
        }
        if (!Encryptor.encrypt(password).equals(user.getPassword())) {
            throw new BusinessException(BusinessExceptionCode.PASSWORD_NOT_VALID);
        }
        utilBean.resetTries(user.getUsername());
    }

    String generateFirstAttemptUsername(final String firstName, final String lastName) {
        StringBuilder username = new StringBuilder();
        if (lastName.length() >= MAX_LAST_NAME_LENGTH) {
            username.append(lastName.substring(0, MAX_LAST_NAME_LENGTH));
            username.append(firstName.charAt(0));
        } else if (lastName.length() + firstName.length() > MIN_USERNAME_LENGTH) {
            username.append(lastName);
            username.append(firstName.substring(0, MIN_USERNAME_LENGTH - lastName.length()));
        } else {
            username.append(lastName);
            username.append(firstName);
            String extra = Stream.iterate(0, i -> i).limit(MIN_USERNAME_LENGTH - username.length())
                    .map(x -> x + "")
                    .reduce("", (x, y) -> x + y);
            username.append(extra);
        }
        return username.toString().toLowerCase();
    }

    String generateRealUsername(@NotNull final String firstName, @NotNull final String lastName) throws BusinessException {
        String realUsername = "";
        String firstAttemptUsername = generateFirstAttemptUsername(firstName, lastName);
        Optional<User> userOptional = userDao.getUserByUsername(firstAttemptUsername);
        if (userOptional.isPresent()) {
            Integer suffixInt;
            String result;
            Long endTime = System.currentTimeMillis() + INFINITE_LOOP_PREVENTION_TIME; //time to find a suffix
            do {
                suffixInt = getRandomInteger(MIN_RANDOM_PREFIX_RANGE, MAX_RANDOM_PREFIX_RANGE);
                result = firstAttemptUsername + suffixInt;
                if (System.currentTimeMillis() > endTime) {
                    throw new BusinessException(BusinessExceptionCode.TOO_MANY_ALIKE_USERNAMES);
                }
            } while (userDao.getUserByUsername(result).isPresent());
            realUsername = result;
        } else {
            realUsername = firstAttemptUsername;
        }
        return realUsername;
    }

    private Integer getRandomInteger(Integer min, Integer max) {
        return generator.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
    }

    @Override
    public void setUserStatus(Long id, UserStatus userStatus) throws BusinessException {
        log.info("setUserStatus: id={}, userStatus={}",id,userStatus);
        Boolean success = userDao.setUserStatus(id, userStatus);
        if(!success){
            throw new BusinessException(BusinessExceptionCode.INVALID_USER);
        }
        log.info("setUserStatus: success");
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws BusinessException {
        log.trace("updateUser: userDto={}", userDto);
        User user = userConverter.convertDtoToEntity(userDto);
        UserValidator.validateUserForUpdate(user);
        if(user.getPassword() != null && !user.getPassword().equals("")){
            user.setPassword(Encryptor.encrypt(user.getPassword()));
        }
        Optional<User> optionalUser = userDao.updateUser(user);
        User userResult = optionalUser
                .orElseThrow(() -> new BusinessException(BusinessExceptionCode.CAN_NOT_UPDATE_USER));
        UserDto result = userConverter.convertEntityToDto(userResult);
        log.trace("updateUser: result={}",result);
        return result;
    }


}
