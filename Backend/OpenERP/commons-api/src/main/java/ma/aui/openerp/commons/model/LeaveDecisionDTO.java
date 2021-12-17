package ma.aui.openerp.commons.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.LeaveState;

@RequiredArgsConstructor
@Getter
public class LeaveDecisionDTO {
    private final String leaveUUID;
    private final LeaveState state;
    private final String comment;
}
