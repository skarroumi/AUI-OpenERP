package ma.aui.openerp.services.employee.projection;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.commands.UserAccountCreationCommand;
import ma.aui.openerp.commons.events.EmployeeBalanceAdjustedEvent;
import ma.aui.openerp.commons.events.EmployeeCreatedEvent;
import ma.aui.openerp.commons.events.EmployeeEditedEvent;
import ma.aui.openerp.commons.util.EventHelper;
import ma.aui.openerp.commons.util.SecurityHelper;
import ma.aui.openerp.services.employee.model.DepartmentEntity;
import ma.aui.openerp.services.employee.model.EmployeeEntity;
import ma.aui.openerp.services.employee.model.JobEntity;
import ma.aui.openerp.services.employee.repository.EmployeeRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.springframework.stereotype.Component;
import sun.jvm.hotspot.oops.Metadata;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class EmployeeProjector {

    private final EmployeeRepository employeeRepository;
    private final CommandGateway commandGateway;
    private final SecurityHelper securityHelper;
    private final EventHelper eventHelper;

    @EventHandler
    public void on(EmployeeCreatedEvent event, MetaData metadata) throws Exception{
        EmployeeEntity employeeEntity = new EmployeeEntity(
                event.getEmployeeId(),
                event.getRegistrationNumber(),
                event.getFirstName(),
                event.getLastName(),
                event.getGender(),
                event.getMarital(),
                event.getBirthDate(),
                event.getJoinDate(),
                null,
                event.getEmail(),
                event.getPhoneNumber(),
                event.getBankAccountNumber(),
                event.getLeaveBalance(),
                new JobEntity(event.getJobId().toString(),"", null),
                new DepartmentEntity(event.getDepartmentId().toString(),"", null),
                event.getRole());

        employeeRepository.save(employeeEntity);
        commandGateway.send(new UserAccountCreationCommand(event.getRegistrationNumber(),
                            securityHelper.generateMD5(event.getRegistrationNumber()),
                            event.getEmployeeId(),
                            event.getRole(),
                            eventHelper.getActor(metadata)));
    }

    @EventHandler
    @Transactional
    public void on(EmployeeBalanceAdjustedEvent event) throws Exception{
        EmployeeEntity employee = employeeRepository.findById(event.getEmployeeId()).get();
        employee.setLeaveBalance(employee.getLeaveBalance()- event.getLeavePeriod());
        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeEditedEvent editedEvent){
        EmployeeEntity editedEmployee = employeeRepository.findById(editedEvent.getEmployeeId()).get();
        editedEmployee.setFirstName(editedEvent.getFirstName());
        editedEmployee.setLastName(editedEvent.getLastName());
        editedEmployee.setMarital(editedEvent.getMarital());
        editedEmployee.setGender(editedEvent.getGender());
        editedEmployee.setBirthDate(editedEvent.getBirthDate());
        editedEmployee.setJoinDate(editedEvent.getJoinDate());
        editedEmployee.setExitDate(editedEvent.getExitDate());
        editedEmployee.setEmail(editedEvent.getEmail());
        editedEmployee.setPhoneNumber(editedEvent.getPhoneNumber());
        editedEmployee.setBankAccountNumber(editedEvent.getBankAccountNumber());
        editedEmployee.setLeaveBalance(editedEvent.getLeaveBalance());
        editedEmployee.setJobId(new JobEntity(editedEvent.getJobId().toString(),"", null));
        editedEmployee.setDepartmentId(new DepartmentEntity(editedEvent.getDepartmentId().toString(),"", null));
        editedEmployee.setRole(editedEvent.getRole());
        employeeRepository.save(editedEmployee);


    }
}
