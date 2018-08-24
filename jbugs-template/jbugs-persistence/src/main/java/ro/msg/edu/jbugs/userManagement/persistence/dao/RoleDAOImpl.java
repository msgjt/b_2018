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
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class RoleDAOImpl implements RoleDAO {

    private Logger log = LogManager.getLogger(RoleDAOImpl.class.getName());

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;


    @Override
    public Optional<Role> updateRole(Role role) {
        log.info("updateRole: role={}", role);
        Optional<Role> roleFound;
        try {
            roleFound = Optional.ofNullable(em.find(Role.class, role.getId()));
        roleFound.map(r -> {
            if(role.getType() != null && !role.getType().equals("")){
                r.setType(role.getType());
            }
            if(role.getPermissions() != null && !role.getPermissions().isEmpty()){
                r.setPermissions(role.getPermissions());
            }
            return r;
        }).orElseThrow(RuntimeException::new);
        } catch (RuntimeException ex) {
            roleFound = Optional.empty();
        }
        log.info("updateRole: result={}", roleFound);
        return roleFound;
    }

    @Override
    public HashSet<Role> getRolesByType(HashSet<RoleType> types) {
        TypedQuery<Role> query = em.createNamedQuery(Role.GET_BY_TYPES, Role.class);
        List<RoleType> typesAsList = new ArrayList<>(types);
        List<String> _types = typesAsList.stream().map(Object::toString).collect(Collectors.toList());
        query.setParameter("types", typesAsList);
        List<Role> roles = query.getResultList();
        HashSet<Role> toReturn = new HashSet<Role>(roles);
        log.info("getRoles: result={}", toReturn.toString());
        return toReturn;
    }

    @Override
    public void addUser(User user, HashSet<Role> roles) {
        for (Role r : roles) {
            Set<User> us = r.getUsers();
            us.add(user);
            r.setUsers(us);
        }
    }

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
