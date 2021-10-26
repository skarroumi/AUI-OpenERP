package ma.aui.openerp.services.authentication.model.converters;

import ma.aui.openerp.commons.enums.Gender;
import ma.aui.openerp.commons.enums.State;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StateConverter implements AttributeConverter<State, String> {

    @Override
    public String convertToDatabaseColumn(State state) {
        switch (state){
            case ENABLED:
                return "1";
            case DISABLED:
                return "0";
            default:
                throw new IllegalArgumentException("State ["+state+"] not Supported!");
        }

    }

    @Override
    public State convertToEntityAttribute(String s) {
        switch (s){
            case "1":
                return State.ENABLED;
            case "0":
                return State.DISABLED;
            default:
                throw new IllegalArgumentException("State ["+s+"] not Supported!");
        }
    }
}
