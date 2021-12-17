package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmployeeBalanceAdjustedEvent {
    private final int leavePeriod;
    private final String employeeUUID;
}
