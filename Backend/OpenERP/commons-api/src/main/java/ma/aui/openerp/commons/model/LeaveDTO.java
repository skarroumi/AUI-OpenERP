package ma.aui.openerp.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.enums.LeaveState;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LeaveDTO {
    private String leaveUUID;
    private String identificationId;
    private String firstName;
    private String lastName;
    private String dateFrom;
    private String dateTo;
    private String reason;
    private LeaveState state;
}
