package ma.aui.openerp.services.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "res.country")
public class CountryEntity {
    @Id
    @Column(name = "code", length = 2)
    private String code;

    @Column(name = "name", length = 30)
    private String name;

    @OneToMany(mappedBy = "countryId")
    private List<EmployeeEntity> memberIds;
}
