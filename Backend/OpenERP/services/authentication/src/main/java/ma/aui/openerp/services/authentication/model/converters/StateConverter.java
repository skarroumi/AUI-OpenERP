package ma.aui.openerp.services.authentication.model.converters;

import ma.aui.openerp.commons.enums.Active;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<Active, String> {

    @Override
    public String convertToDatabaseColumn(Active active) {
        switch (active){
            case ENABLED:
                return "1";
            case DISABLED:
                return "0";
            default:
                throw new IllegalArgumentException("State ["+active+"] not Supported!");
        }

    }

    @Override
    public Active convertToEntityAttribute(String s) {
        switch (s){
            case "1":
                return Active.ENABLED;
            case "0":
                return Active.DISABLED;
            default:
                throw new IllegalArgumentException("Active ["+s+"] not Supported!");
        }
    }
}
