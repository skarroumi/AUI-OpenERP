package ma.aui.openerp.commons.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LeaveInProgressForManagerSearchQuery {
    private final String managerIdentificationId;
}
