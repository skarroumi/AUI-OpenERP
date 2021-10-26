package ma.aui.openerp.services.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.enums.*;
import ma.aui.openerp.services.employee.model.converters.GenderConverter;
import ma.aui.openerp.services.employee.model.converters.MaritalConverter;
import ma.aui.openerp.services.employee.model.converters.UserRoleConverter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tr_employees")
public class EmployeeEntity {
    @Id
    @Column(length = 36)
    private String employeeId;
    @Column(length = 5)
    private String registrationNumber;
    @Column(length = 15)
    private String firstName;
    @Column(length = 15)
    private String lastName;
    @Column(length = 1)
    @Convert(converter = GenderConverter.class)
    private Gender gender;
    @Column(length = 2)
    @Convert(converter = MaritalConverter.class)
    private Marital marital;
    @Column(length = 10)
    private String birthDate;
    @Column(length = 10)
    private String joinDate;
    @Column(length = 10)
    private String exitDate;
    @Column(length = 30)
    private String email;
    @Column(length = 15)
    private String phoneNumber;
    @Column(length = 24)
    private String bankAccountNumber;
    private int leaveBalance;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity jobId;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentId;
    @Column(length = 5)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

}
