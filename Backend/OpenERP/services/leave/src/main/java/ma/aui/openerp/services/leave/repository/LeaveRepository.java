package ma.aui.openerp.services.leave.repository;

import ma.aui.openerp.services.leave.model.LeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveEntity, String> {

    @Query(value = "select l from LeaveEntity l where l.managerRegistrationNumber=:managerId and l.state='1'")
    List<LeaveEntity> getLeaveInProgress(@Param("managerId")String managerId);

    @Query(value = "select l from LeaveEntity l where l.managerRegistrationNumber=:managerId")
    List<LeaveEntity> getAllLeavesForManager(@Param("managerId")String managerId);

    @Query(value = "select l from LeaveEntity l where l.registrationNumber=:employeeRegistrationNumber")
    List<LeaveEntity> getAllLeavesForEmployee(@Param("employeeRegistrationNumber")String employeeRegistrationNumber);
}
