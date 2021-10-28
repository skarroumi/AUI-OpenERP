package ma.aui.openerp.commons.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.model.ActorDTO;
import ma.aui.openerp.commons.model.NewEmployeeDTO;

@Getter
@RequiredArgsConstructor
public class EmployeeCreationCommand {
    private final ActorDTO actor;
    private final NewEmployeeDTO newEmployee;
}
