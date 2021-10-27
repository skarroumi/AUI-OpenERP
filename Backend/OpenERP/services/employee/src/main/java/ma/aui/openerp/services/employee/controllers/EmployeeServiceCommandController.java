package ma.aui.openerp.services.employee.controllers;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.apis.IEmployeeServiceCommandController;
import ma.aui.openerp.commons.commands.EmployeeCreationCommand;
import ma.aui.openerp.commons.commands.EmployeeEditCommand;
import ma.aui.openerp.commons.model.EmployeeCreationDTO;
import ma.aui.openerp.commons.model.EmployeeEditDTO;
import ma.aui.openerp.commons.model.EmployeeEditDTOComposite;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees/api/v1")
public class EmployeeServiceCommandController implements IEmployeeServiceCommandController {

    private final CommandGateway commandGateway;

    @PostMapping(value = "/employees")
    @Override
    public CompletableFuture<String> addNewEmployee(@RequestBody EmployeeCreationDTO employeeCreationDTO) {
        return commandGateway.send(new EmployeeCreationCommand(employeeCreationDTO.getActor(), employeeCreationDTO.getEmployee()));

    }


    @PutMapping(value = "/employees/edit")
    @Override
    public CompletableFuture<Void> editEmployee(@RequestBody EmployeeEditDTOComposite employeeEditDTOComposite) {
        System.out.println("employeeEditDTOComposite.getEmployeeEdit().getEmployeeId()");
        return commandGateway.send(new EmployeeEditCommand(
                employeeEditDTOComposite.getEmployeeEdit().getEmployeeId(),
                employeeEditDTOComposite.getActor(),
                employeeEditDTOComposite.getEmployeeEdit()));

    }
}
