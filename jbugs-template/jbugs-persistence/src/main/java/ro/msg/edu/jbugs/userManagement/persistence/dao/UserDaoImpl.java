package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    public Optional<User> addUser(User user) {
        log.info("addUser: user={}", user);
        Optional<User> userOptional;
        try {
            em.persist(user);
            em.flush();
            userOptional = Optional.ofNullable(user);
        } catch (RuntimeException ex) {
            userOptional = Optional.empty();
        }
        log.info("addUser: result={}", user);
        return userOptional;
    }

    @Override
    public Optional<User> updateUser(User user) {
        log.info("updateUser: user={}", user);
        Optional<User> userFound;
        try {
            userFound = Optional.ofNullable(em.find(User.class, user.getId()));
            userFound.map(u -> {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setStatus(user.getStatus());
                u.setEmail(user.getEmail());
                u.setMobileNumber(user.getMobileNumber());
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

}
