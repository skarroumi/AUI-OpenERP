package ma.aui.openerp.services.leave.repository;

import ma.aui.openerp.services.leave.model.LeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveEntity, String> {

    @Query(value = "select l from LeaveEntity l where l.managerIdentificationId=:managerIdentificationId and l.state='1'")
    List<LeaveEntity> getLeaveInProgress(@Param("managerIdentificationId")String managerIdentificationId);

    @Query(value = "select l from LeaveEntity l where l.managerIdentificationId=:managerIdentificationId")
    List<LeaveEntity> getAllLeavesForManager(@Param("managerIdentificationId")String managerIdentificationId);

    @Query(value = "select l from LeaveEntity l where l.identificationId=:identificationId")
    List<LeaveEntity> getAllLeavesForEmployee(@Param("identificationId")String identificationId);
}
