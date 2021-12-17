package ma.aui.openerp.commons.commands;

import lombok.Getter;
import ma.aui.openerp.commons.model.ActorDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class EmployeeBalanceAdjustmentCommand {
    @TargetAggregateIdentifier
    private final String employeeUUID;
    private final ActorDTO actor;
    private final int leavePeriod;

    public EmployeeBalanceAdjustmentCommand(String employeeUUID, ActorDTO actor, int leavePeriod) {
        this.employeeUUID = employeeUUID;
        this.actor = actor;
        this.leavePeriod = leavePeriod;
    }
}
