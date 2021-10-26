package ma.aui.openerp.commons.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.LeaveState;
import ma.aui.openerp.commons.model.ActorDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class LeaveDecisionCommand {

    @TargetAggregateIdentifier
    private final String leaveId;
    private final ActorDTO actor;
    private final LeaveState leaveState;
    private final String comment;

    public LeaveDecisionCommand(ActorDTO actor, String leaveId, LeaveState leaveState, String comment){
        this.leaveId = leaveId;
        this.actor = actor;
        this.leaveState = leaveState;
        this.comment = comment;
    }

}
