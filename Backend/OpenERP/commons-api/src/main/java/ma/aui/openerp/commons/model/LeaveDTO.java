package ma.aui.openerp.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.enums.LeaveState;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LeaveDTO {
    private String id;
    private String registrationNumber;
    private String firstName;
    private String lastName;
    private int leaveBalance;
    private String startDate;
    private String endDate;
    private String reason;
    private LeaveState state;
}
