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
import ma.aui.openerp.commons.util.EventHelper;
import ma.aui.openerp.commons.util.OpenERPHelper;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
public class EmployeeAggregate {
    @AggregateIdentifier
    private String employeeUUID;
    private String identificationId;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Marital marital;
    private String birthDay;
    private String joinDay;
    private String exitDay;
    private String workEmail;
    private String mobilePhone;
    private String bankAccountNumber;
    private int leaveBalance;
    private Department departmentId;
    private Job jobId;
    private UserRole role;
    private Country countryId;

    @CommandHandler
    public EmployeeAggregate(EmployeeCreationCommand cmd, EventHelper eventHelper, OpenERPHelper openERPHelper){
        String employeeId = UUID.randomUUID().toString();
        String registrationNumber = openERPHelper.generateIdentificationId(5);
        EmployeeCreatedEvent employeeCreatedEvent = new EmployeeCreatedEvent(
                employeeId,
                registrationNumber,
                cmd.getNewEmployee().getFirstName(),
                cmd.getNewEmployee().getLastName(),
                cmd.getNewEmployee().getGender(),
                cmd.getNewEmployee().getMarital(),
                cmd.getNewEmployee().getBirthDay(),
                cmd.getNewEmployee().getJoinDay(),
                cmd.getNewEmployee().getWorkEmail(),
                cmd.getNewEmployee().getMobilePhone(),
                cmd.getNewEmployee().getBankAccountNumber(),
                cmd.getNewEmployee().getLeaveBalance(),
                cmd.getNewEmployee().getJobId(),
                cmd.getNewEmployee().getDepartmentId(),
                cmd.getNewEmployee().getRole(),
                cmd.getNewEmployee().getCountryId());
        eventHelper.dispatchEvent(employeeCreatedEvent, cmd.getActor());
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event){
        this.employeeUUID = event.getEmployeeUUID();
        this.identificationId = event.getIdentificationId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.gender = event.getGender();
        this.marital = event.getMarital();
        this.birthDay = event.getBirthDay();
        this.joinDay = event.getJoinDay();
        this.workEmail = event.getWorkEmail();
        this.mobilePhone = event.getMobilePhone();
        this.bankAccountNumber = event.getBankAccountNumber();
        this.leaveBalance = event.getLeaveBalance();
        this.departmentId = event.getDepartmentId();
        this.jobId = event.getJobId();
        this.role = event.getRole();
        this.countryId = event.getCountryId();
    }

    @CommandHandler
    public void handle(EmployeeBalanceAdjustmentCommand employeeBalanceAdjustmentCommand, EventHelper eventHelper) {
        EmployeeBalanceAdjustedEvent employeeBalanceAdjustedEvent = new EmployeeBalanceAdjustedEvent(
                employeeBalanceAdjustmentCommand.getLeavePeriod(),
                employeeBalanceAdjustmentCommand.getEmployeeUUID());
        eventHelper.dispatchEvent(employeeBalanceAdjustedEvent, employeeBalanceAdjustmentCommand.getActor());
    }

    @EventSourcingHandler
    public void on(EmployeeBalanceAdjustedEvent event){
        this.leaveBalance = this.leaveBalance - event.getLeavePeriod();
    }

    @CommandHandler
    public void handle(EmployeeEditCommand employeeEditCommand, EventHelper eventHelper){
        EmployeeEditedEvent editedEmployee = new EmployeeEditedEvent(
                employeeEditCommand.getEmployeeUUID(),
                employeeEditCommand.getEmployee().getFirstName(),
                employeeEditCommand.getEmployee().getLastName(),
                employeeEditCommand.getEmployee().getGender(),
                employeeEditCommand.getEmployee().getMarital(),
                employeeEditCommand.getEmployee().getBirthDay(),
                employeeEditCommand.getEmployee().getJoinDay(),
                employeeEditCommand.getEmployee().getExitDay(),
                employeeEditCommand.getEmployee().getWorkEmail(),
                employeeEditCommand.getEmployee().getMobilePhone(),
                employeeEditCommand.getEmployee().getBankAccountNumber(),
                employeeEditCommand.getEmployee().getLeaveBalance(),
                employeeEditCommand.getEmployee().getJobId(),
                employeeEditCommand.getEmployee().getDepartmentId(),
                employeeEditCommand.getEmployee().getRole(),
                employeeEditCommand.getEmployee().getCountryId());
        eventHelper.dispatchEvent(editedEmployee, employeeEditCommand.getActor());
    }

    @EventSourcingHandler
    public void on(EmployeeEditedEvent eventEdit){
        this.firstName = eventEdit.getFirstName();
        this.lastName = eventEdit.getLastName();
        this.gender = eventEdit.getGender();
        this.marital = eventEdit.getMarital();
        this.birthDay = eventEdit.getBirthDay();
        this.joinDay = eventEdit.getJoinDay();
        this.exitDay = eventEdit.getExitDay();
        this.workEmail = eventEdit.getWorkEmail();
        this.mobilePhone = eventEdit.getMobilePhone();
        this.bankAccountNumber = eventEdit.getBankAccountNumber();
        this.leaveBalance = eventEdit.getLeaveBalance();
        this.departmentId = eventEdit.getDepartmentId();
        this.jobId = eventEdit.getJobId();
        this.role = eventEdit.getRole();
        this.countryId = eventEdit.getCountryId();
    }
}
