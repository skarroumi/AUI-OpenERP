package ma.aui.openerp.services.leave.model.converters;

import ma.aui.openerp.commons.enums.LeaveState;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LeaveStateConverter implements AttributeConverter<LeaveState, String> {
    
    @Override
    public String convertToDatabaseColumn(LeaveState leaveState) {
        switch (leaveState){
            case IN_PROGRESS:
                return "1";
            case REJECTED:
                return "2";
            case APPROVED:
                return "3";
            default:
                throw new IllegalArgumentException("LeaveState ["+leaveState+"] not Supported!");
        }
    }

    @Override
    public LeaveState convertToEntityAttribute(String s) {
        switch (s){
            case "1":
                return LeaveState.IN_PROGRESS;
            case "2":
                return LeaveState.REJECTED;
            case "3":
                return LeaveState.APPROVED;
            default:
                throw new IllegalArgumentException("LeaveState ["+s+"] not Supported!");
        }
    }
}

