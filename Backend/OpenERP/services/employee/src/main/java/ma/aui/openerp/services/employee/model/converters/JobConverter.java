package ma.aui.openerp.services.employee.model.converters;

import com.google.gson.annotations.SerializedName;
import ma.aui.openerp.commons.enums.Gender;
import ma.aui.openerp.commons.enums.Job;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class JobConverter implements AttributeConverter<Job, String> {

    @Override
    public String convertToDatabaseColumn(Job job) {
        switch (job){
            case CONSULTANT:
                return "CNS";
            case DEVELOPER:
                return "DEV";
            case PROJECT_MANAGER:
                return "PM";
            case ARCHITECT:
                return "ARCH";
            case TESTER:
                return "TST";
            case TEAM_LEAD:
                return "TL";
            default:
                throw new IllegalArgumentException("Job ["+job+"] not Supported!");
        }

    }

    @Override
    public Job convertToEntityAttribute(String s) {
        switch (s){
            case "CNS":
                return Job.CONSULTANT;
            case "DEV":
                return Job.DEVELOPER;
            case "PM":
                return Job.PROJECT_MANAGER;
            case "ARCH":
                return Job.ARCHITECT;
            case "TST":
                return Job.TESTER;
            case "TL":
                return Job.TEAM_LEAD;
            default:
                throw new IllegalArgumentException("Job ["+s+"] not Supported!");
        }
    }
}

