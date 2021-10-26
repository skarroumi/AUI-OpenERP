package ma.aui.openerp.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class NewLeaveDTO {
    private String registrationNumber;
    private String startDate;
    private String endDate;
    private String reason;
}
