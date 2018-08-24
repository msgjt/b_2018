package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;

import java.util.List;

public interface BugService {
    /**
     * This method is used to persist a bug in database
     * @param bugDto is the bug that will be persisted
     * @return The newly created bug as a bugDto which have the generated id.
     * @throws BusinessException
     */
    BugDto createBug(BugDto bugDto) throws BusinessException;


//    /**
//     *
//     * @return
//     */
//    List<BugDto> getBugs();

}
