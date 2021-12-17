package ma.aui.openerp.commons.commands;

import lombok.Getter;
import ma.aui.openerp.commons.enums.LeaveState;
import ma.aui.openerp.commons.model.ActorDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class LeaveDecisionCommand {

    @TargetAggregateIdentifier
    private final String leaveUUID;
    private final ActorDTO actor;
    private final LeaveState state;
    private final String comment;

    public LeaveDecisionCommand(ActorDTO actor, String leaveUUID, LeaveState state, String comment){
        this.leaveUUID = leaveUUID;
        this.actor = actor;
        this.state = state;
        this.comment = comment;
    }

}
