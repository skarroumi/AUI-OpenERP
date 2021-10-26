package ma.aui.openerp.services.employee.model.converters;

import ma.aui.openerp.commons.enums.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole role) {
        switch (role){
            case SIMPLE_USER:
                return "SU";
            case MANAGER:
                return "MGR";
            default:
                throw new IllegalArgumentException("UserRole ["+role+"] not Supported!");
        }

    }

    @Override
    public UserRole convertToEntityAttribute(String s) {
        switch (s){
            case "SU":
                return UserRole.SIMPLE_USER;
            case "MGR":
                return UserRole.MANAGER;
            default:
                throw new IllegalArgumentException("UserRole ["+s+"] not Supported!");
        }
    }
}
