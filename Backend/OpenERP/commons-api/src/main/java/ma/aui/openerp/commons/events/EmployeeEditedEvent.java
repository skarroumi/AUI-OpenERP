package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.*;

@Getter
@RequiredArgsConstructor
public class EmployeeEditedEvent {
    private final String employeeId;
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final Marital marital;
    private final String birthDate;
    private final String joinDate;
    private final String exitDate;
    private final String email;
    private final String phoneNumber;
    private final String bankAccountNumber;
    private final int leaveBalance;
    private final Job jobId;
    private final Department departmentId;
    private final UserRole role;
}
