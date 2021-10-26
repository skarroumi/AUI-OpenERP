package ma.aui.openerp.commons.queries;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AllEmployeesInDepartmentSearchQuery {
    private final String departmentId;
}
