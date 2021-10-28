package ma.aui.openerp.services.employee.model.converters;

import ma.aui.openerp.commons.enums.Marital;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MaritalConverter implements AttributeConverter<Marital, String> {
    
    @Override
    public String convertToDatabaseColumn(Marital marital) {
        switch (marital){
            case SINGLE:
                return "SI";
            case MARRIED:
                return "MA";
            case WIDOWED:
                return "WI";
            case DIVORCED:
                return "DI";
            default:
                throw new IllegalArgumentException("Marital ["+marital+"] not Supported!");
        }
    }

    @Override
    public Marital convertToEntityAttribute(String s) {
        switch (s){
            case "SI":
                return Marital.SINGLE;
            case "MA":
                return Marital.MARRIED;
            case "WI":
                return Marital.WIDOWED;
            case "DI":
                return Marital.DIVORCED;
            default:
                throw new IllegalArgumentException("Marital ["+s+"] not Supported!");
        }
    }
}

