package ma.aui.openerp.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class NewLeaveDTO {
    private String identificationId;
    private String dateFrom;
    private String dateTo;
    private String reason;
}
