package ma.aui.openerp.services.leave.controllers;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.apis.ILeaveServiceCommandController;
import ma.aui.openerp.commons.commands.EmployeeCreationCommand;
import ma.aui.openerp.commons.commands.LeaveCreationCommand;
import ma.aui.openerp.commons.commands.LeaveDecisionCommand;
import ma.aui.openerp.commons.model.LeaveCreationDTO;
import ma.aui.openerp.commons.model.LeaveDecisionDTOComposite;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/leaves/api/v1")
public class LeaveServiceCommandController implements ILeaveServiceCommandController {

    private final CommandGateway commandGateway;

    @PostMapping(value = "/leaves")
    @Override
    public CompletableFuture<String> addNewLeave(@RequestBody LeaveCreationDTO leaveCreationDTO) {
        return commandGateway.send(new LeaveCreationCommand(leaveCreationDTO.getActor(), leaveCreationDTO.getLeave()));
    }

    @PutMapping(value = "/leaves")
    @Override
    public CompletableFuture<Void> decideOnLeave(@RequestBody LeaveDecisionDTOComposite leaveDecisionDTOComposite) {
        return commandGateway.send(new LeaveDecisionCommand(leaveDecisionDTOComposite.getActor(),
                leaveDecisionDTOComposite.getDecision().getLeaveId(),
                leaveDecisionDTOComposite.getDecision().getState(),
                leaveDecisionDTOComposite.getDecision().getComment()));
    }
}
