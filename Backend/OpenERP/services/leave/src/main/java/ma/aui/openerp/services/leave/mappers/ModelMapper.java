package ma.aui.openerp.services.leave.mappers;

import ma.aui.openerp.commons.model.LeaveDTO;
import ma.aui.openerp.services.leave.model.LeaveEntity;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public LeaveDTO convert(LeaveEntity entity){
        LeaveDTO dto = new LeaveDTO(
                entity.getId(),
                entity.getRegistrationNumber(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getLeaveBalance(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getReason(),
                entity.getState());
        return dto;
    }
}
