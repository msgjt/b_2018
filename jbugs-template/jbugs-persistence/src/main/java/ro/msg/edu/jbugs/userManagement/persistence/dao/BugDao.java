package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.enums.BugStatusType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Api for bug persistence
 */

public interface BugDao {
    /**
     * method header for adding a bug
     * @param bug  a bug is sent as parameter to be introduced in Database
     * @return this function returns an Optional of type Bug containing the ug sent as parameter
     */
    Optional<Bug> addBug(@NotNull Bug bug);

    Optional<Bug> closeBug(@NotNull Long bugId);

    Optional<Bug> changeBugStatus(@NotNull Long bugId, @NotNull BugStatusType bugStatusType);
}
