package ro.msg.edu.jbugs.userManagement.persistence.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PermissionDAOImpl implements PermissionDAO {

    private Logger log = LogManager.getLogger(PermissionDAOImpl.class.getName());

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager entityManager;

    @Override
    public List<Permission> getAllPermissions() {
        log.info("getAllPermissions: --- entered");
        TypedQuery<Permission> query = entityManager.createNamedQuery(Permission.GET_ALL_PERMISSIONS, Permission.class);
        List<Permission> permissions = query.getResultList();
        log.info("getAllPermissions: result={}", permissions);
        return permissions;
    }

    @Override
    public List<Permission> getRolePermissionsById(Long id) {
        log.info("getRolePermissionsById: id={}", id);
        TypedQuery<Permission> query = entityManager.createNamedQuery(Permission.GET_ROLE_PERMISSIONS_BY_ID, Permission.class);
        query.setParameter("id", id);
        List<Permission> permissions = query.getResultList();
        log.info("getRolePermissionsById: result={}", permissions);
        return permissions;
    }
}
