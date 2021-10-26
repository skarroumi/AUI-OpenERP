package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.LeaveState;

@Getter
@RequiredArgsConstructor
public class LeaveCreatedEvent {
    private final String id;
    private final String registrationNumber;
    private final String startDate;
    private final String endDate;
    private final String reason;
    private final LeaveState state;
    private final String managerRegistrationNumber;
    private final String firstName;
    private final String lastName;
    private final int leaveBalance;

}
