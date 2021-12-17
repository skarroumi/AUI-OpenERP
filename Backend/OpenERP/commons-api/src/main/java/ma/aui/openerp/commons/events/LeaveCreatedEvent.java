package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.LeaveState;

@Getter
@RequiredArgsConstructor
public class LeaveCreatedEvent {
    private final String leaveUUID;
    private final String identificationId;
    private final String dateFrom;
    private final String dateTo;
    private final String reason;
    private final LeaveState state;
    private final String managerIdentificationId;
    private final String firstName;
    private final String lastName;

}
