package ro.msg.edu.jbugs.userManagement.persistence.entity.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum BugStatusType {
    OPEN,
    IN_PROGRESS,
    INFO_NEEDED,
    FIXED,
    CLOSED,
    REJECTED;

    static {
        OPEN.setNextStatus(IN_PROGRESS,REJECTED);
        FIXED.setNextStatus(OPEN, CLOSED);
        IN_PROGRESS.setNextStatus(REJECTED, INFO_NEEDED, FIXED);
        REJECTED.setNextStatus(CLOSED);
        INFO_NEEDED.setNextStatus(IN_PROGRESS);
    }

    @Getter
    private List<BugStatusType> nextStatus = new ArrayList<>();

    private void setNextStatus(BugStatusType... nextStatus) {
        //Arrays.stream(nextStatus).forEach(this.nextStatus::add);
        Collections.addAll(this.nextStatus, nextStatus);
    }

}
