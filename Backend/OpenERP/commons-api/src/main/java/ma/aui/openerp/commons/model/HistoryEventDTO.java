package ma.aui.openerp.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryEventDTO implements Serializable {

    private String eventId;
    private String eventCanonicalName;
    private String eventName;
    private String eventDescription;
    private String aggregateIdentifier;
    private String timestamp;
    private Object payload;
    private ActorDTO actor;

}
