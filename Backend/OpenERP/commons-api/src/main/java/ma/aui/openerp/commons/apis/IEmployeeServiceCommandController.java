package ma.aui.openerp.commons.apis;

import ma.aui.openerp.commons.model.EmployeeCreationDTO;
import ma.aui.openerp.commons.model.EmployeeEditDTOComposite;
import java.util.concurrent.CompletableFuture;

public interface IEmployeeServiceCommandController {
    CompletableFuture<String> addNewEmployee(EmployeeCreationDTO employeeCreationDTO);
    CompletableFuture<Void> editEmployee(EmployeeEditDTOComposite employeeEditDTOComposite);
}
