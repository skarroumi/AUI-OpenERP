package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.LeaveState;

@RequiredArgsConstructor
@Getter
public class LeaveDecidedOnEvent {
    private final String leaveId;
    private final LeaveState state;
    private final String comment;
}
