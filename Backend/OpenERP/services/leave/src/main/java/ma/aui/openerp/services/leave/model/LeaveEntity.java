package ma.aui.openerp.services.leave.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.enums.LeaveState;
import ma.aui.openerp.services.leave.model.converters.LeaveStateConverter;

import javax.persistence.*;

@Entity
@Table(name = "hr.leaves")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class LeaveEntity {
    @Id
    @Column(name = "leave_uuid")
    private String leaveUUID;

    @Column(name = "identification_id", length = 5)
    private String identificationId;

    @Column(name = "first_name" , length = 15)
    private String firstName;

    @Column(name = "last_name" , length = 15)
    private String lastName;

    @Column(name = "date_from" , length = 10)
    private String dateFrom;

    @Column(name = "date_to" , length = 10)
    private String dateTo;

    @Column(name = "reason", length = 20)
    private String reason;

    @Convert(converter = LeaveStateConverter.class)
    @Column(name = "state", length = 1)
    private LeaveState state;

    @Column(name = "manager_identification_id", length = 5)
    private String managerIdentificationId;

    @Column(name = "comment", length = 100)
    private String comment;
}
