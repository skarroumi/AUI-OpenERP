package ma.aui.openerp.services.leave.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.commands.LeaveCreationCommand;
import ma.aui.openerp.commons.commands.LeaveDecisionCommand;
import ma.aui.openerp.commons.enums.LeaveState;
import ma.aui.openerp.commons.events.LeaveCreatedEvent;
import ma.aui.openerp.commons.events.LeaveDecidedOnEvent;
import ma.aui.openerp.commons.exceptions.*;
import ma.aui.openerp.commons.model.EmployeeDTO;
import ma.aui.openerp.commons.queries.DepartmentManagerSearchQuery;
import ma.aui.openerp.commons.queries.EmployeeRegistrationNumberSearchQuery;
import ma.aui.openerp.commons.util.EventHelper;
import ma.aui.openerp.commons.util.OpenERPHelper;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Aggregate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Aggregate
@NoArgsConstructor
@Getter @Setter
public class LeaveAggregate {
    @AggregateIdentifier
    private String id;
    private String registrationNumber;
    private String firstName;
    private String lastName;
    private String startDate;
    private String endDate;
    private String reason;
    private LeaveState state;
    private String managerRegistrationNumber;
    private String comment;

    @CommandHandler
    public LeaveAggregate(LeaveCreationCommand cmd, EventHelper eventHelper, OpenERPHelper openERPHelper, QueryGateway queryGateway)throws SystemException, InvalidDateException, InvalidDateIntervalException, InsufficientLeaveBalanceException, EmployeeNotFoundException, Exception {


            if (!openERPHelper.isValidDate(cmd.getNewLeave().getStartDate()))
                throw new InvalidDateException("Invalid Start Date Value "+ cmd.getNewLeave().getStartDate()+"!");

            if (!openERPHelper.isValidDate(cmd.getNewLeave().getEndDate()))
                throw new InvalidDateException("Invalid End Date Value "+ cmd.getNewLeave().getEndDate()+"!");

            if(!openERPHelper.isValidLeaveDateInterval(cmd.getNewLeave().getStartDate(), cmd.getNewLeave().getEndDate()))
                throw new InvalidDateIntervalException("Invalid Interval dates!");

            String leaveId = UUID.randomUUID().toString();

            CompletableFuture<EmployeeDTO> queryReply = queryGateway.query(new EmployeeRegistrationNumberSearchQuery(cmd.getNewLeave().getRegistrationNumber()), ResponseTypes.instanceOf(EmployeeDTO.class));
            EmployeeDTO employeeDTO = queryReply.get();

            String startDate2 = openERPHelper.replace(cmd.getNewLeave().getStartDate(),"^([0-9]{4})-([0-9]{2})-([0-9]{2})$","$3/$2/$1");
            String endDate2 = openERPHelper.replace(cmd.getNewLeave().getEndDate(),"^([0-9]{4})-([0-9]{2})-([0-9]{2})$","$3/$2/$1");
            int leavePeriod = openERPHelper.leavePeriod(startDate2, endDate2);


            if(leavePeriod>employeeDTO.getLeaveBalance())
                throw new InsufficientLeaveBalanceException("Insufficient Leave Balance");

            CompletableFuture<EmployeeDTO> managerQueryReply = queryGateway.query(new DepartmentManagerSearchQuery(employeeDTO.getDepartmentId().toString()), ResponseTypes.instanceOf(EmployeeDTO.class));
            EmployeeDTO managerDTO = managerQueryReply.get();

            LeaveCreatedEvent event = new LeaveCreatedEvent(
                    leaveId,
                    cmd.getNewLeave().getRegistrationNumber(),
                    cmd.getNewLeave().getStartDate(),
                    cmd.getNewLeave().getEndDate(),
                    cmd.getNewLeave().getReason(),
                    LeaveState.IN_PROGRESS,
                    managerDTO.getRegistrationNumber(),
                    employeeDTO.getFirstName(),
                    employeeDTO.getLastName());
            eventHelper.dispatchEvent(event, cmd.getActor());

    }

    @EventSourcingHandler
    public void on(LeaveCreatedEvent event){
        this.id = event.getId();
        this.registrationNumber = event.getRegistrationNumber();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.reason = event.getReason();
        this.state = event.getState();
        this.managerRegistrationNumber = event.getManagerRegistrationNumber();

    }

    @CommandHandler
    public void handle(LeaveDecisionCommand leaveDecisionCommand, EventHelper eventHelper){
        LeaveDecidedOnEvent updatedEvent = new LeaveDecidedOnEvent(
                leaveDecisionCommand.getLeaveId(),
                leaveDecisionCommand.getLeaveState(),
                leaveDecisionCommand.getComment()
        );
        eventHelper.dispatchEvent(updatedEvent, leaveDecisionCommand.getActor());
    }

    @EventSourcingHandler
    public void on(LeaveDecidedOnEvent event){
        this.state = event.getState();
        this.comment = event.getComment();
    }
}
