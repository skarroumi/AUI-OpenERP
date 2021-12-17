package ma.aui.openerp.services.employee.repository;

import ma.aui.openerp.services.employee.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    EmployeeEntity findByIdentificationId(String number);

    @Query(value = "select e from EmployeeEntity e where e.departmentId.departmentId=:departmentId")
    List<EmployeeEntity> findEmployeesByDepartment(@Param("departmentId") String departmentId);

    @Query(value = "select e from EmployeeEntity e where e.departmentId.departmentId=:departmentId and e.role='MGR'")
    EmployeeEntity findDepartmentManager(@Param("departmentId") String departmentId);
}
