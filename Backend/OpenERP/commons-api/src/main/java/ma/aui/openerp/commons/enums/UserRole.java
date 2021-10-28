package ma.aui.openerp.commons.enums;

import com.google.gson.annotations.SerializedName;

public enum UserRole {
    @SerializedName("SU")
    SIMPLE_USER,
    @SerializedName("MGR")
    MANAGER;

}
