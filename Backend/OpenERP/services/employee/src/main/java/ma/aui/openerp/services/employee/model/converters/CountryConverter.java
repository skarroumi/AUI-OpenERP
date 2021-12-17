package ma.aui.openerp.services.employee.model.converters;

import ma.aui.openerp.commons.enums.Country;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CountryConverter implements AttributeConverter<Country, String> {
    @Override
    public String convertToDatabaseColumn(Country country) {
        switch (country){
            case MOROCCO:
                return "MA";
            case FRANCE:
                return "FR";
            case UNITED_STATES_OF_AMERICA:
                return "US";
            case GERMANY:
                return "DE";
            default:
                throw new IllegalArgumentException("Country ["+country+"] not Supported!");
        }
    }

    @Override
    public Country convertToEntityAttribute(String s) {
        switch (s){
            case "MA":
                return Country.MOROCCO;
            case "FR":
                return Country.FRANCE;
            case "US":
                return Country.UNITED_STATES_OF_AMERICA;
            case "DE":
                return Country.GERMANY;
            default:
                throw new IllegalArgumentException("Country ["+s+"] not Supported!");
        }
    }
}
