package ma.aui.openerp.commons.apis;

import ma.aui.openerp.commons.model.LeaveDTO;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ILeaveServiceQueryController {
    CompletableFuture<List<LeaveDTO>> getInProgressLeavesForManager(String managerId);
    CompletableFuture<List<LeaveDTO>> getAllLeavesForManager(String managerId);
    CompletableFuture<List<LeaveDTO>> getAllLeavesForEmployee(String employeeRegistrationNumber);
}
