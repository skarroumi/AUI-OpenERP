package ma.aui.openerp.services.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hr.department")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentEntity {
    @Id
    @Column(name = "department_id", length = 10)
    private String departmentId;
    @Column(name = "complete_name", length = 50)
    private String completeName;
    @OneToMany(mappedBy = "departmentId")
    private List<EmployeeEntity> memberIds;
}
