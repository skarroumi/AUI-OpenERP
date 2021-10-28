package ma.aui.openerp.services.employee.mappers;

import ma.aui.openerp.commons.enums.Department;
import ma.aui.openerp.commons.enums.Job;
import ma.aui.openerp.commons.model.EmployeeDTO;
import ma.aui.openerp.services.employee.model.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public EmployeeDTO convert(EmployeeEntity entity){
        EmployeeDTO dto = new EmployeeDTO(
                entity.getEmployeeId(),
                entity.getRegistrationNumber(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getGender(),
                entity.getMarital(),
                entity.getBirthDate(),
                entity.getJoinDate(),
                entity.getExitDate(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getBankAccountNumber(),
                entity.getLeaveBalance(),
                Job.fromString(entity.getJobId().getJobId()),
                Department.fromString(entity.getDepartmentId().getDepartmentId()),
                entity.getRole());

        return dto;
    }

}
