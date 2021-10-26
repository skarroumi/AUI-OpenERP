package ma.aui.openerp.commons.enums;

import com.google.gson.annotations.SerializedName;

public enum Marital {
    @SerializedName("SI")
    SINGLE,
    @SerializedName("MA")
    MARRIED,
    @SerializedName("WI")
    WIDOWED,
    @SerializedName("DI")
    DIVORCED
}
