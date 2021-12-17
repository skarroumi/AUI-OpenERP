package ma.aui.openerp.services.leave.controllers;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.apis.ILeaveServiceCommandController;
import ma.aui.openerp.commons.commands.LeaveCreationCommand;
import ma.aui.openerp.commons.commands.LeaveDecisionCommand;
import ma.aui.openerp.commons.model.LeaveCreationDTO;
import ma.aui.openerp.commons.model.LeaveDecisionDTOComposite;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/leaves/api/v1")
public class LeaveServiceCommandController implements ILeaveServiceCommandController {

    private final CommandGateway commandGateway;

    @PreAuthorize("hasAnyAuthority('MGR','SU')")
    @PostMapping(value = "/leaves")
    @Override
    public CompletableFuture<String> addNewLeave(@RequestBody LeaveCreationDTO leaveCreationDTO) {
        return commandGateway.send(new LeaveCreationCommand(leaveCreationDTO.getActor(), leaveCreationDTO.getLeave()));
    }

    @PreAuthorize("hasAuthority('MGR')")
    @PutMapping(value = "/leaves")
    @Override
    public CompletableFuture<Void> decideOnLeave(@RequestBody LeaveDecisionDTOComposite leaveDecisionDTOComposite) {
        return commandGateway.send(new LeaveDecisionCommand(
                leaveDecisionDTOComposite.getActor(),
                leaveDecisionDTOComposite.getDecision().getLeaveUUID(),
                leaveDecisionDTOComposite.getDecision().getState(),
                leaveDecisionDTOComposite.getDecision().getComment()));
    }
}
