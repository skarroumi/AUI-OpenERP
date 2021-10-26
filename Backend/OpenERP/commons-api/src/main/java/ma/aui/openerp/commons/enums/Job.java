package ma.aui.openerp.commons.enums;

import com.google.gson.annotations.SerializedName;

public enum Job {
    @SerializedName("CNS")
    CONSULTANT{
        public String toString(){
            return "CNS";
        }
    },
    @SerializedName("DEV")
    DEVELOPER{
        public String toString(){
            return "DEV";
        }
    },
    @SerializedName("PM")
    PROJECT_MANAGER{
        public String toString(){
            return "PM";
        }
    },
    @SerializedName("ARCH")
    ARCHITECT{
        public String toString(){
            return "ARCH";
        }
    },
    @SerializedName("TST")
    TESTER{
        public String toString(){
            return "TST";
        }
    },
    @SerializedName("TL")
    TEAM_LEAD{
        public String toString(){
            return "TL";
        }
    };

    public static Job fromString(String value){
        Job job = null;
        if (value.equalsIgnoreCase("CNS"))
            job = Job.CONSULTANT;

        if (value.equalsIgnoreCase("DEV"))
            job = Job.DEVELOPER;

        if (value.equalsIgnoreCase("PM"))
            job = Job.PROJECT_MANAGER;

        if (value.equalsIgnoreCase("ARCH"))
            job = Job.ARCHITECT;

        if (value.equalsIgnoreCase("TST"))
            job = Job.TESTER;

        if (value.equalsIgnoreCase("TL"))
            job = Job.TEAM_LEAD;

        return job;
    }
}
