package ma.aui.openerp.commons.apis;

import ma.aui.openerp.commons.model.LeaveCreationDTO;
import ma.aui.openerp.commons.model.LeaveDecisionDTOComposite;

import java.util.concurrent.CompletableFuture;

public interface ILeaveServiceCommandController {
    CompletableFuture<String> addNewLeave(LeaveCreationDTO leaveCreationDTO);
    CompletableFuture<Void> decideOnLeave(LeaveDecisionDTOComposite leaveDecisionDTOComposite);
}
