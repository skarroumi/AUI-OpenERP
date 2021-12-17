package ma.aui.openerp.commons.apis;

import ma.aui.openerp.commons.exceptions.EmployeeNotFoundException;
import ma.aui.openerp.commons.exceptions.ManagerNotFoundException;
import ma.aui.openerp.commons.model.EmployeeDTO;
import ma.aui.openerp.commons.model.HistoryEventDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IEmployeeServiceQueryController {
    List<HistoryEventDTO> getEmployeeHistoryEvents(String employeeUUID);
    CompletableFuture<EmployeeDTO> getEmployeeByIdentificationId(String identificationId) throws EmployeeNotFoundException;
    CompletableFuture<List<EmployeeDTO>> getAllEmployees();
    CompletableFuture<List<EmployeeDTO>> getAllEmployeesByDepartment(String departmentId);
    CompletableFuture<EmployeeDTO> getDepartmentManager(String departmentId) throws ManagerNotFoundException;
}
