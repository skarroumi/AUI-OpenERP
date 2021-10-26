package ma.aui.openerp.services.employee.model.converters;

import ma.aui.openerp.commons.enums.Department;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DepartmentConverter implements AttributeConverter<Department, String> {
    
    @Override
    public String convertToDatabaseColumn(Department department) {
        switch (department){
            case HUMAN_RESOURCES:
                return "HR";
            case INFORMATION_TECHNOLOGY:
                return "IT";
            case MARKETING:
                return "MKT";
            case FINANCE:
                return "FN";
            default:
                throw new IllegalArgumentException("Department ["+department+"] not Supported!");
        }
    }

    @Override
    public Department convertToEntityAttribute(String s) {
        switch (s){
            case "HR":
                return Department.HUMAN_RESOURCES;
            case "IT":
                return Department.INFORMATION_TECHNOLOGY;
            case "MKT":
                return Department.MARKETING;
            case "FN":
                return Department.FINANCE;
            default:
                throw new IllegalArgumentException("Department ["+s+"] not Supported!");
        }
    }
}

