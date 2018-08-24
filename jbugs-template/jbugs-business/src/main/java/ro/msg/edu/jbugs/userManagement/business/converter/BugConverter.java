package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.enterprise.context.RequestScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequestScoped
public class BugConverter extends AbstractConverterBaseEntityConverter<Bug, BugDto> {

    @Override
    public Bug convertDtoToEntity(BugDto bugDto) {
        UserConverter userConverter= new UserConverter();
        return Bug.builder()
                .title(bugDto.getTitle())
                .description(bugDto.getDescription())
                .version(bugDto.getVersion())
                .fixedInVersion(bugDto.getFixedInVersion())
                .dueDate(this.convertDate(bugDto.getDueDate()))
                .severityType(bugDto.getSeverityType())
                .statusType(bugDto.getBugStatusType())
                .creator(userConverter.convertDtoToEntity(bugDto.getAssignee()))
                .assignee(userConverter.convertDtoToEntity(bugDto.getCreator()))
                .build();
    }

    @Override
    public BugDto convertEntityToDto(Bug bug) {
        UserConverter userConverter= new UserConverter();
        BugDto bugDto = BugDto.builder()
                .title(bug.getTitle())
                .description(bug.getDescription())
                .version(bug.getVersion())
                .fixedInVersion(bug.getFixedInVersion())
                .dueDate(bug.getDueDate().toString())
                .severityType(bug.getSeverityType())
                .bugStatusType(bug.getStatusType())
                .creator(userConverter.convertEntityToDto(bug.getAssignee()))
                .assignee(userConverter.convertEntityToDto(bug.getCreator()))
                .build();
        bugDto.setId(bug.getId());
        return bugDto;
    }

    public java.util.Date convertDate(String stringDate){

        try {
            java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
