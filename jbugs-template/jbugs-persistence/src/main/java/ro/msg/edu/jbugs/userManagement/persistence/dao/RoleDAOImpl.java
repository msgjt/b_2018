package ro.msg.edu.jbugs.userManagement.persistence.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.RoleType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class RoleDAOImpl implements RoleDAO {

    private Logger log = LogManager.getLogger(RoleDAOImpl.class.getName());

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public List<Role> getAllRoles() {
        log.info("getAllRoles: --- entered");
        TypedQuery<Role> query = em.createNamedQuery(Role.GET_ALL_ROLES, Role.class);
        List<Role> roles = query.getResultList();
        log.info("getAllRoles: result={}", roles);
        return roles;
    }

    @Override
    public List<Role> getUserRolesById(Long id) {
        log.info("getUserRolesById: id={}",id);
        TypedQuery<Role> query = em.createNamedQuery(Role.GET_USER_ROLES_BY_ID, Role.class);
        query.setParameter("id", id);
        List<Role> roles = query.getResultList();
        log.info("getUserRolesById: result={}", roles);
        return roles;
    }
}
