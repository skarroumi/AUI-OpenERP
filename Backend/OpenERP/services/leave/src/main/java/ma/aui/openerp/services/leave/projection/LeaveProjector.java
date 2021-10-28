package ma.aui.openerp.services.leave.projection;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.commands.EmployeeBalanceAdjustmentCommand;
import ma.aui.openerp.commons.enums.LeaveState;
import ma.aui.openerp.commons.events.LeaveCreatedEvent;
import ma.aui.openerp.commons.events.LeaveDecidedOnEvent;
import ma.aui.openerp.commons.exceptions.EmployeeNotFoundException;
import ma.aui.openerp.commons.model.EmployeeDTO;
import ma.aui.openerp.commons.queries.EmployeeRegistrationNumberSearchQuery;
import ma.aui.openerp.commons.util.EventHelper;
import ma.aui.openerp.commons.util.OpenERPHelper;
import ma.aui.openerp.services.leave.model.LeaveEntity;
import ma.aui.openerp.services.leave.repository.LeaveRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class LeaveProjector {

    private final LeaveRepository leaveRepository;
    private final OpenERPHelper openERPHelper;
    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;
    private final EventHelper eventHelper;

    @EventHandler
    public void on(LeaveCreatedEvent event) throws Exception{
        LeaveEntity leaveEntity = new LeaveEntity(
                event.getId(),
                event.getRegistrationNumber(),
                event.getFirstName(),
                event.getLastName(),
                event.getLeaveBalance(),
                event.getStartDate(),
                event.getEndDate(),
                event.getReason(),
                event.getState(),
                event.getManagerRegistrationNumber(),
                ""
        );
        leaveRepository.save(leaveEntity);
    }

    @EventHandler
    @Transactional
    public void on(LeaveDecidedOnEvent event, MetaData metaData) throws Exception, EmployeeNotFoundException {
        LeaveEntity leaveEntity = leaveRepository.findById(event.getLeaveId()).get();
        leaveEntity.setState(event.getState());
        leaveEntity.setComment(event.getComment());
        leaveRepository.save(leaveEntity);

        if (event.getState()== LeaveState.APPROVED) {
            String startDate2 = openERPHelper.replace(leaveEntity.getStartDate(), "^([0-9]{4})-([0-9]{2})-([0-9]{2})$", "$3/$2/$1");
            String endDate2 = openERPHelper.replace(leaveEntity.getEndDate(), "^([0-9]{4})-([0-9]{2})-([0-9]{2})$", "$3/$2/$1");
            int leavePeriod = openERPHelper.leavePeriod(startDate2, endDate2);

            CompletableFuture<EmployeeDTO> employeeEntity = queryGateway.query(new EmployeeRegistrationNumberSearchQuery(leaveEntity.getRegistrationNumber()), ResponseTypes.instanceOf(EmployeeDTO.class));
            EmployeeDTO employee = employeeEntity.get();
            //System.out.println(employee.getEmployeeId() + employee.getLeaveBalance());
            commandGateway.send(new EmployeeBalanceAdjustmentCommand(employee.getEmployeeId(), eventHelper.getActor(metaData), leavePeriod));
        }
    }

}
