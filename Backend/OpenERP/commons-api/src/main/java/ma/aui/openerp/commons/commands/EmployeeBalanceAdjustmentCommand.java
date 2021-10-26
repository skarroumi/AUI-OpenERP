package ma.aui.openerp.commons.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.model.ActorDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class EmployeeBalanceAdjustmentCommand {
    @TargetAggregateIdentifier
    private final String employeeId;
    private final ActorDTO actor;
    private final int leavePeriod;

    public EmployeeBalanceAdjustmentCommand(String employeeId, ActorDTO actor, int leavePeriod) {
        this.employeeId = employeeId;
        this.actor = actor;
        this.leavePeriod = leavePeriod;
    }
}
