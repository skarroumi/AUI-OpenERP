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


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hr.employee")
public class EmployeeEntity {
    //NOT
    @Id
    @Column(name = "employee_UUID", length = 36)
    private String employeeUUID;

    //Length not
    @Column(name = "identification_id", length = 5)
    private String identificationId;

    //NOT
    @Column(name = "first_name",length = 15)
    private String firstName;

    //NOT
    @Column(name = "last_name", length = 15)
    private String lastName;

    @Column(name = "gender", length = 1)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "marital", length = 2)
    @Convert(converter = MaritalConverter.class)
    private Marital marital;

    //type not
    @Column(name = "birthday", length = 10)
    private String birthDay;

    //not
    @Column(name = "joinday", length = 10)
    private String joinDay;

    //not
    @Column(name = "exitday", length = 10)
    private String exitDay;

    @Column(name = "work_email",length = 240)
    private String workEmail;

    @Column(name = "mobile_phone", length = 32)
    private String mobilePhone;

    //should be bank_account_id
    @Column(name = "bank_account_number", length = 64)
    private String bankAccountNumber;

    //not
    @Column(name = "leave_balance", length = 2)
    private int leaveBalance;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity jobId;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity departmentId;

    //not
    @Column(name = "role", length = 5)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity countryId;

}
