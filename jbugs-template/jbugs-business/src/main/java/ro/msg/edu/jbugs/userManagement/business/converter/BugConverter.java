package ro.msg.edu.jbugs.userManagement.business.converter;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class BugConverter extends AbstractConverterBaseEntityConverter<Bug, BugDto> {

    @Override
    public Bug convertDtoToEntity(BugDto bugDto) {
        return Bug.builder()
                .title(bugDto.getTitle())
                .description(bugDto.getDescription())
                .version(bugDto.getVersion())
                .fixedInVersion(bugDto.getFixedInVersion())
                .dueDate(bugDto.getDueDate())
                .severityType(bugDto.getSeverityType())
                .statusType(bugDto.getBugStatusType())
                .creator(bugDto.getCreator())
                .assignee(bugDto.getAssignee())
                .build();
    }

    @Override
    public BugDto convertEntityToDto(Bug bug) {
        return BugDto.builder()
                .title(bug.getTitle())
                .description(bug.getDescription())
                .version(bug.getVersion())
                .fixedInVersion(bug.getFixedInVersion())
                .dueDate(bug.getDueDate())
                .severityType(bug.getSeverityType())
                .bugStatusType(bug.getStatusType())
                .creator(bug.getCreator())
                .assignee(bug.getAssignee())
                .build();
    }
}
