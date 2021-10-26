package ma.aui.openerp.commons.enums;

import com.google.gson.annotations.SerializedName;

public enum UserRole {
    @SerializedName("SU")
    SIMPLE_USER,
    @SerializedName("MGR")
    MANAGER;

    /*public static UserRole fromString(String value){
        UserRole userRole = null;
        if (value.equalsIgnoreCase("SU"))
            userRole = UserRole.SIMPLE_USER;

        if (value.equalsIgnoreCase("MGR"))
            userRole = UserRole.MANAGER;

        return userRole;
    }*/
}
