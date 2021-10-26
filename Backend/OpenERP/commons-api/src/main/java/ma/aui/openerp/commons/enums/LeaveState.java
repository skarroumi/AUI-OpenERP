package ma.aui.openerp.commons.enums;

import com.google.gson.annotations.SerializedName;

public enum LeaveState {
    @SerializedName("1")
    IN_PROGRESS,
    @SerializedName("2")
    REJECTED,
    @SerializedName("3")
    APPROVED
}
