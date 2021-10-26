package ma.aui.openerp.commons.enums;

import com.google.gson.annotations.SerializedName;

public enum Department {
    @SerializedName("HR")
    HUMAN_RESOURCES{
        public String toString(){
            return "HR";
        }
    },
    @SerializedName("IT")
    INFORMATION_TECHNOLOGY{
        public String toString(){
            return "IT";
        }
    },
    @SerializedName("MKT")
    MARKETING{
        public String toString(){
            return "MKT";
        }
    },
    @SerializedName("FN")
    FINANCE{
        public String toString(){
            return "FN";
        }
    };

    public static Department fromString(String value){
        Department department = null;
        if (value.equalsIgnoreCase("HR"))
            department = Department.HUMAN_RESOURCES;

        if (value.equalsIgnoreCase("IT"))
            department = Department.INFORMATION_TECHNOLOGY;

        if (value.equalsIgnoreCase("MKT"))
            department = Department.MARKETING;

        if (value.equalsIgnoreCase("FN"))
            department = Department.FINANCE;

        return department;
    }
}
