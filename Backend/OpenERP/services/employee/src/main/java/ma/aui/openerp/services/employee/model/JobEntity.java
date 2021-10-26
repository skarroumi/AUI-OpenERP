package ma.aui.openerp.services.employee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tr_jobs")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class JobEntity {
    @Id
    @Column(name = "id", length = 10)
    private String jobId;
    @Column(name = "name", length = 50)
    private String name;
    @OneToMany(mappedBy = "jobId")
    private List<EmployeeEntity> employees;
}
