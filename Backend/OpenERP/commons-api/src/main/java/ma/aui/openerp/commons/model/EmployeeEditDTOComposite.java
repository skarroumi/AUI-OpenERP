package ma.aui.openerp.commons.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeEditDTOComposite {
    private final ActorDTO actor;
    private final EmployeeEditDTO employeeEdit;
}
