package ma.aui.openerp.services.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tr_departments")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {
    @Id
    @Column(name = "id", length = 10)
    private String departmentId;
    @Column(name = "name", length = 50)
    private String name;
    @OneToMany(mappedBy = "departmentId")
    private List<EmployeeEntity> employees;
}
