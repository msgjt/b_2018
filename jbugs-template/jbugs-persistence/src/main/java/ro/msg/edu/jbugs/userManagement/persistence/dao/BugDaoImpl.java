package ro.msg.edu.jbugs.userManagement.persistence.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BugDaoImpl implements BugDao {

    private Logger log = LogManager.getLogger(BugDaoImpl.class.getName());
    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    /**
     * Function for adding a bug in database.
     * Can be called with a bug, it will be persisted in the database and the method will return an Optional
     * with the type bug
     * Before you run this method be sure you have valid users in database,either it will give an Exeption
     * @param bug  a bug is sent as parameter to be introduced in Database
     * @return this function returns an Optional of type Bug containing the ug sent as parameter
     */

    @Override
    public Optional<Bug> addBug(Bug bug) {
        log.info("addBug: bug={}", bug);
        Optional<Bug> bugOptional;
        try {
            em.persist(bug);
            em.flush();
            bugOptional = Optional.ofNullable(bug);
        } catch (RuntimeException ex) {
            bugOptional = Optional.empty();
            log.info("bug not inserted");
            log.info(ex.getStackTrace());
        }
        log.info("addBug: result={}", bug);
        return bugOptional;
    }

}
