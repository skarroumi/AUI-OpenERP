package ma.aui.openerp.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.enums.*;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class EmployeeEditDTO implements Serializable {
    private String employeeUUID;
    //private String identificationId;
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
    private Job jobId;
    private Department departmentId;
    private UserRole role;
    private Country countryId;
}
