package ma.aui.openerp.commons.enums;

import com.google.gson.annotations.SerializedName;

public enum Country {
    @SerializedName("MA")
    MOROCCO{
        public String toString(){
            return "MA";
        }
    },
    @SerializedName("FR")
    FRANCE{
        public String toString(){
            return "FR";
        }
    },
    @SerializedName("US")
    UNITED_STATES_OF_AMERICA{
        public String toString(){
            return "US";
        }
    },
    @SerializedName("DE")
    GERMANY{
        public String toString(){
            return "DE";
        }
    };

    public static Country fromString(String value){
        Country country = null;
        if (value.equalsIgnoreCase("MA"))
            country = Country.MOROCCO;

        if (value.equalsIgnoreCase("FR"))
            country = Country.FRANCE;

        if (value.equalsIgnoreCase("US"))
            country = Country.UNITED_STATES_OF_AMERICA;

        if (value.equalsIgnoreCase("DE"))
            country = Country.GERMANY;

        return country;
    }
}
