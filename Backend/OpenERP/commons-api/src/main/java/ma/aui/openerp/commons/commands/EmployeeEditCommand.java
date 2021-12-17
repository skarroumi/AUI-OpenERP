package ma.aui.openerp.commons.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.model.ActorDTO;
import ma.aui.openerp.commons.model.EmployeeEditDTO;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
@Getter
public class EmployeeEditCommand {
    @TargetAggregateIdentifier
    private final String employeeUUID;
    private final ActorDTO actor;
    private final EmployeeEditDTO employee;
}
