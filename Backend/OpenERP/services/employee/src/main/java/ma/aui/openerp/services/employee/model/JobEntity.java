package ma.aui.openerp.services.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hr.job")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class JobEntity {
    @Id
    @Column(name = "job_id", length = 10)
    private String jobId;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "description", length = 128)
    private String description;

    @OneToMany(mappedBy = "jobId")
    private List<EmployeeEntity> employeeIds;

}
