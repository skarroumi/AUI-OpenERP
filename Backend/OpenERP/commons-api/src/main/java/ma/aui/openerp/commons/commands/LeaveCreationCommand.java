package ma.aui.openerp.commons.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.model.ActorDTO;
import ma.aui.openerp.commons.model.NewEmployeeDTO;
import ma.aui.openerp.commons.model.NewLeaveDTO;

@Getter
@RequiredArgsConstructor
public class LeaveCreationCommand {
    private final ActorDTO actor;
    private final NewLeaveDTO newLeave;
}
