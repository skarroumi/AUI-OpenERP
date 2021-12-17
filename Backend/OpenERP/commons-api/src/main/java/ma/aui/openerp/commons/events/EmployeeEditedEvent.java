package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.*;

@Getter
@RequiredArgsConstructor
public class EmployeeEditedEvent {
    private final String employeeUUID;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final Marital marital;
    private final String birthDay;
    private final String joinDay;
    private final String exitDay;
    private final String workEmail;
    private final String mobilePhone;
    private final String bankAccountNumber;
    private final int leaveBalance;
    private final Job jobId;
    private final Department departmentId;
    private final UserRole role;
    private final Country countryId;
}
