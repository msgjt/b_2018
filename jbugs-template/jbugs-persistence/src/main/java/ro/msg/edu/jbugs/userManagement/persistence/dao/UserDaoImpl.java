package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.UserStatus;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDaoImpl implements UserDao {
    private Logger log = LogManager.getLogger(UserDaoImpl.class.getName());

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public Optional<User> createUser(User user) {
        log.info("createUser: user={}", user);
        Optional<User> userOptional;
        try {
            em.persist(user);
            userOptional = Optional.of(user);
        } catch (RuntimeException ex) {
            userOptional = Optional.empty();
        }
        log.info("createUser: result={}", user);
        return userOptional;
    }

    @Override
    public Optional<User> updateUser(User user) {
        log.info("updateUser: user={}", user);
        Optional<User> userFound;
        try {
            userFound = Optional.ofNullable(em.find(User.class, user.getId()));
            userFound.map(u -> {
                if(user.getFirstName()!= null && !user.getFirstName().equals(""))
                        u.setFirstName(user.getFirstName());
                if(user.getLastName()!= null && !user.getLastName().equals(""))
                    u.setLastName(user.getLastName());
                if(user.getEmail()!= null && !user.getEmail().equals(""))
                    u.setEmail(user.getEmail());
                if(user.getMobileNumber()!= null && !user.getMobileNumber().equals(""))
                    u.setMobileNumber(user.getMobileNumber());
                if(user.getPassword()!= null && !user.getPassword().equals(""))
                    u.setPassword(user.getPassword());
                if(user.getRoles()!= null && !user.getRoles().isEmpty()){
                    u.setRoles(user.getRoles());
                }
                return u;
            }).orElseThrow(RuntimeException::new);
        } catch (RuntimeException ex) {
            userFound = Optional.empty();
        }
        log.info("updateUser: result={}", userFound);
        return userFound;
    }

    @Override
    public List<User> getAllUsers() {
        log.info("getAllUsers: --- entered");
        TypedQuery<User> query = em.createNamedQuery(User.GET_ALL_USERS, User.class);
        List<User> users = query.getResultList();
        log.info("getAllUsers: result={}", users);
        return users;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        log.info("getUserByUsername: username={}", username);
        Optional<User> userOptional;
        try {
            TypedQuery<User> query = em.createNamedQuery(User.GET_USER_BY_USERNAME, User.class);
            query.setParameter("username", username);
            userOptional = Optional.of(query.getSingleResult());
        } catch (RuntimeException ex) {
            userOptional = Optional.empty();
        }
        log.info("getUserByUsername: result={}", userOptional);
        return userOptional;
    }

    @Override
    public Optional<User> getUserByUsernameWithRolesAndPermissions(String username) {
        log.info("getUserByUsernameWithRolesAndPermissions: username={}", username);
        Optional<User> userOptional;
        try {
            TypedQuery<User> query = em.createNamedQuery(User.GET_USER_BY_USERNAME_WITH_ROLES_AND_PERMISSIONS, User.class);
            query.setParameter("username", username);
            userOptional = Optional.of(query.getSingleResult());
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            userOptional = Optional.empty();
        }
        log.info("getUserByUsernameWithRolesAndPermissions: result={}", userOptional);
        return userOptional;
    }

    @Override
    public Boolean setUserStatus(Long id, UserStatus userStatus) {
        log.info("setUserStatus: id={}", id);
        Optional<User> user = Optional.ofNullable(em.find(User.class, id));
        user = user.map(u -> {
            u.setStatus(userStatus);
            return u;
        });
        log.info("setUserStatus: user={}", user);
        return user.isPresent();
    }

    @Override
    public Boolean setUserStatusByUsername(String username, UserStatus userStatus) {
        log.info("setUserStatus: username={}", username);
        Query query = em.createNamedQuery(User.UPDATE_USER_STATUS_BY_USERNAME);
        query.setParameter("status", userStatus);
        query.setParameter("username", username);
        final Integer result = query.executeUpdate();
        log.info("setUserStatus: result={}", result);
        return result != 0;
    }

    @Override
    public Optional<User> getUserWithEmail(String email) {
        log.info("getUserWithEmail: email={}", email);
        Optional<User> userOptional;
        try {
            TypedQuery<User> query = em.createNamedQuery(User.GET_USER_BY_EMAIL, User.class);
            query.setParameter("email", email);
            userOptional = Optional.of(query.getSingleResult());
        } catch (RuntimeException ex) {
            userOptional = Optional.empty();
        }
        log.info("getUserWithEmail: result={}", userOptional);
        return userOptional;
    }

    @Override
    public Boolean hasOpenBugsByUsername(String username) {
        log.info("hasOpenBugsByUsername: username={}", username);
        TypedQuery<Bug> query = em.createNamedQuery(User.COUNT_OPEN_BUGS_BY_USERNAME, Bug.class);
        query.setParameter("username", username);
        query.setParameter("status", BugStatusType.CLOSED);
        List<Bug> openBugs = query.getResultList();
        log.info("hasOpenBugsByUsername: result={}", openBugs.size());
        return !openBugs.isEmpty();
    }
}
