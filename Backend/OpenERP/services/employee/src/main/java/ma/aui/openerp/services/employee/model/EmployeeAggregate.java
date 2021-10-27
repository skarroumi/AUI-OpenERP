package ma.aui.openerp.services.employee.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.commands.EmployeeBalanceAdjustmentCommand;
import ma.aui.openerp.commons.commands.EmployeeCreationCommand;
import ma.aui.openerp.commons.commands.EmployeeEditCommand;
import ma.aui.openerp.commons.enums.*;
import ma.aui.openerp.commons.events.EmployeeBalanceAdjustedEvent;
import ma.aui.openerp.commons.events.EmployeeCreatedEvent;
import ma.aui.openerp.commons.events.EmployeeEditedEvent;
import ma.aui.openerp.commons.exceptions.EmployeeNotFoundException;
import ma.aui.openerp.commons.model.EmployeeEditDTO;
import ma.aui.openerp.commons.util.EventHelper;
import ma.aui.openerp.commons.util.OpenERPHelper;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
public class EmployeeAggregate {
    @AggregateIdentifier
    private String employeeId;
    private String registrationNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Marital marital;
    private String birthDate;
    private String joinDate;
    private String exitDate;
    private String email;
    private String phoneNumber;
    private String bankAccountNumber;
    private int leaveBalance;
    private Department departmentId;
    private Job jobId;
    private UserRole role;

    @CommandHandler
    public EmployeeAggregate(EmployeeCreationCommand cmd, EventHelper eventHelper, OpenERPHelper openERPHelper){
        String employeeId = UUID.randomUUID().toString();
        String registrationNumber = openERPHelper.generateRegistrationNumber(5);
        EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent(
                employeeId,
                registrationNumber,
                cmd.getNewEmployee().getFirstName(),
                cmd.getNewEmployee().getLastName(),
                cmd.getNewEmployee().getGender(),
                cmd.getNewEmployee().getMarital(),
                cmd.getNewEmployee().getBirthDate(),
                cmd.getNewEmployee().getJoinDate(),
                cmd.getNewEmployee().getEmail(),
                cmd.getNewEmployee().getPhoneNumber(),
                cmd.getNewEmployee().getBankAccountNumber(),
                cmd.getNewEmployee().getLeaveBalance(),
                cmd.getNewEmployee().getJobId(),
                cmd.getNewEmployee().getDepartmentId(),
                cmd.getNewEmployee().getRole());
        eventHelper.dispatchEvent(employeeCreatedEvent, cmd.getActor());
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event){
        this.employeeId = event.getEmployeeId();
        this.registrationNumber = event.getRegistrationNumber();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.gender = event.getGender();
        this.marital = event.getMarital();
        this.birthDate = event.getBirthDate();
        this.joinDate = event.getJoinDate();
        this.email = event.getEmail();
        this.phoneNumber = event.getPhoneNumber();
        this.bankAccountNumber = event.getBankAccountNumber();
        this.leaveBalance = event.getLeaveBalance();
        this.departmentId = event.getDepartmentId();
        this.jobId = event.getJobId();
        this.role = event.getRole();
    }

    @CommandHandler
    public void handle(EmployeeBalanceAdjustmentCommand employeeBalanceAdjustmentCommand, EventHelper eventHelper) {
        EmployeeBalanceAdjustedEvent employeeBalanceAdjustedEvent = new EmployeeBalanceAdjustedEvent(
                employeeBalanceAdjustmentCommand.getLeavePeriod(),
                employeeBalanceAdjustmentCommand.getEmployeeId());
        eventHelper.dispatchEvent(employeeBalanceAdjustedEvent, employeeBalanceAdjustmentCommand.getActor());
    }

    @EventSourcingHandler
    public void on(EmployeeBalanceAdjustedEvent event){
        this.leaveBalance = this.leaveBalance - event.getLeavePeriod();
    }

    @CommandHandler
    public void handle(EmployeeEditCommand employeeEditCommand, EventHelper eventHelper){
        EmployeeEditedEvent editedEmployee = new EmployeeEditedEvent(
                employeeEditCommand.getEmployeeId(),
                employeeEditCommand.getEmployee().getFirstName(),
                employeeEditCommand.getEmployee().getLastName(),
                employeeEditCommand.getEmployee().getGender(),
                employeeEditCommand.getEmployee().getMarital(),
                employeeEditCommand.getEmployee().getBirthDate(),
                employeeEditCommand.getEmployee().getJoinDate(),
                employeeEditCommand.getEmployee().getExitDate(),
                employeeEditCommand.getEmployee().getEmail(),
                employeeEditCommand.getEmployee().getPhoneNumber(),
                employeeEditCommand.getEmployee().getBankAccountNumber(),
                employeeEditCommand.getEmployee().getLeaveBalance(),
                employeeEditCommand.getEmployee().getJobId(),
                employeeEditCommand.getEmployee().getDepartmentId(),
                employeeEditCommand.getEmployee().getRole());
        eventHelper.dispatchEvent(editedEmployee, employeeEditCommand.getActor());
    }

    @EventSourcingHandler
    public void on(EmployeeEditedEvent eventEdit){
        this.firstName = eventEdit.getFirstName();
        this.lastName = eventEdit.getLastName();
        this.gender = eventEdit.getGender();
        this.marital = eventEdit.getMarital();
        this.birthDate = eventEdit.getBirthDate();
        this.joinDate = eventEdit.getJoinDate();
        this.exitDate = eventEdit.getExitDate();
        this.email = eventEdit.getEmail();
        this.phoneNumber = eventEdit.getPhoneNumber();
        this.bankAccountNumber = eventEdit.getBankAccountNumber();
        this.leaveBalance = eventEdit.getLeaveBalance();
        this.departmentId = eventEdit.getDepartmentId();
        this.jobId = eventEdit.getJobId();
        this.role = eventEdit.getRole();
    }
}
