package ma.aui.openerp.services.leave.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.aui.openerp.commons.enums.LeaveState;
import ma.aui.openerp.services.leave.model.converters.LeaveStateConverter;

import javax.persistence.*;

@Entity
@Table(name = "tr_leaves")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class LeaveEntity {
    @Id
    private String id;
    @Column(length = 5)
    private String registrationNumber;
    @Column(length = 15)
    private String firstName;
    @Column(length = 15)
    private String lastName;
    @Column(length = 10)
    private String startDate;
    @Column(length = 10)
    private String endDate;
    @Column(length = 20)
    private String reason;
    @Convert(converter = LeaveStateConverter.class)
    @Column(length = 1)
    private LeaveState state;
    @Column(length = 5)
    private String managerRegistrationNumber;
    @Column(length = 100)
    private String comment;
}
