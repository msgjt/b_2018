package ro.msg.edu.jbugs.userManagement.persistence.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.IOException;
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
        Optional<Bug> bugOptional ;
        try {
            em.persist(bug);
            bugOptional = Optional.of(bug);
        } catch (RuntimeException ex) {
            bugOptional = Optional.empty();
            log.info("bug not inserted");
            log.info(ex.getStackTrace());
        }
        log.info("addBug: result={}", bugOptional);
        return bugOptional;
    }

    @Override
    public Optional<Bug> closeBug(Long bugId) {
        log.info("close Bug: bugId={}", bugId);
        Optional<Bug> bugOptional;
        try {
            bugOptional = Optional.ofNullable(em.find(Bug.class, bugId));
            log.info("optionalBug: bugOptiona:{}", bugOptional);
            bugOptional.map(b -> {
                if (b.getStatusType().equals(BugStatusType.FIXED) || b.getStatusType().equals(BugStatusType.REJECTED)) {
                    b.setStatusType(BugStatusType.CLOSED);
                    em.merge(b);
                    return b;
                }
                else {
                    return null;
                }
            }).orElseThrow(RuntimeException::new);
        } catch (RuntimeException ex) {
            bugOptional = Optional.empty();
        }
        log.info("closeBug: result={}", bugOptional);
        return bugOptional;
    }

    @Override
    public Optional<Bug> changeBugStatus(Long bugId, BugStatusType bugStatusType) {
        log.info("change status Bug: bugId={} newStatus={}", bugId, bugStatusType);
        Optional<Bug> bugOptional;
        try {
            bugOptional = Optional.ofNullable(em.find(Bug.class, bugId));
            log.info("optionalBug: bugOptional:{}", bugOptional);
            bugOptional.map(b -> {
                if ( b.getStatusType().getNextStatus().contains(bugStatusType)) {
                    b.setStatusType(bugStatusType);
                    log.info("bug to update in db: bug={}", b);
                }
                    return b;
            }).orElseThrow(RuntimeException::new);
        } catch (RuntimeException ex) {
            log.info("exceptioooon!!!");
            bugOptional = Optional.empty();
        }
        log.info("change status bug: result={}", bugOptional);
        return bugOptional;
    }
}
