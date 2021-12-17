package ma.aui.openerp.services.leave.mappers;

import ma.aui.openerp.commons.model.LeaveDTO;
import ma.aui.openerp.services.leave.model.LeaveEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public LeaveDTO convert(LeaveEntity entity){
        LeaveDTO dto = new LeaveDTO(
                entity.getLeaveUUID(),
                entity.getIdentificationId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDateFrom(),
                entity.getDateTo(),
                entity.getReason(),
                entity.getState());
        return dto;
    }
}
