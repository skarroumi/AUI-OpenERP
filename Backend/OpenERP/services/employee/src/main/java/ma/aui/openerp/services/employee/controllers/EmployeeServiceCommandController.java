package ma.aui.openerp.services.employee.controllers;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.apis.IEmployeeServiceCommandController;
import ma.aui.openerp.commons.commands.EmployeeCreationCommand;
import ma.aui.openerp.commons.model.EmployeeCreationDTO;
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
}
