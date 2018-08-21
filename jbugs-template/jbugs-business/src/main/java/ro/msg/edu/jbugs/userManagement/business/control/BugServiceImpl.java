package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.converter.BugConverter;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.business.exception.BusinessException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugDao;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;
import javax.inject.Inject;

public class BugServiceImpl implements BugService{

    @EJB
    BugDao bugDao;

    @Inject
    BugConverter bugConverter;

    @Override
    public BugDto createBug(BugDto bugDto) throws BusinessException {
        //log.info("createBug: bugDto={}", bugDto);
        Bug bug =bugConverter.convertDtoToEntity(bugDto);
        
        return null;
    }
}
