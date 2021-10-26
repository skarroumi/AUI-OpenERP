package ma.aui.openerp.services.leave.controllers;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.apis.ILeaveServiceQueryController;
import ma.aui.openerp.commons.model.LeaveDTO;
import ma.aui.openerp.commons.queries.AllLeavesForEmployeeSearchQuery;
import ma.aui.openerp.commons.queries.LeaveForManagerSearchQuery;
import ma.aui.openerp.commons.queries.LeaveInProgressForManagerSearchQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/leaves/api/v1")
public class LeaveServiceQueryController implements ILeaveServiceQueryController {

    private final QueryGateway queryGateway;

    @Override
    @GetMapping(value = "/managers/{managerId}/leaves/inprogress")
    public CompletableFuture<List<LeaveDTO>> getInProgressLeavesForManager(@PathVariable String managerId){
        return queryGateway.query(new LeaveInProgressForManagerSearchQuery(managerId), ResponseTypes.multipleInstancesOf(LeaveDTO.class));
    }

    @Override
    @GetMapping(value = "/managers/{managerId}/leaves")
    public CompletableFuture<List<LeaveDTO>> getAllLeavesForManager(@PathVariable String managerId){
        return queryGateway.query(new LeaveForManagerSearchQuery(managerId), ResponseTypes.multipleInstancesOf(LeaveDTO.class));
    }

    @Override
    @GetMapping(value = "/employees/{employeeRegistrationNumber}/leaves")
    public CompletableFuture<List<LeaveDTO>> getAllLeavesForEmployee(@PathVariable String employeeRegistrationNumber){
        return queryGateway.query(new AllLeavesForEmployeeSearchQuery(employeeRegistrationNumber), ResponseTypes.multipleInstancesOf(LeaveDTO.class));
    }
}
