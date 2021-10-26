package ma.aui.openerp.services.employee.controllers;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.apis.IEmployeeServiceQueryController;
import ma.aui.openerp.commons.exceptions.EmployeeNotFoundException;
import ma.aui.openerp.commons.exceptions.ManagerNotFoundException;
import ma.aui.openerp.commons.model.EmployeeDTO;
import ma.aui.openerp.commons.model.HistoryEventDTO;
import ma.aui.openerp.commons.queries.AllEmployeesInDepartmentSearchQuery;
import ma.aui.openerp.commons.queries.AllEmployeesSearchQuery;
import ma.aui.openerp.commons.queries.DepartmentManagerSearchQuery;
import ma.aui.openerp.commons.queries.EmployeeRegistrationNumberSearchQuery;
import ma.aui.openerp.commons.util.EventHelper;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Path;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees/api/v1")
public class EmployeeServiceQueryController implements IEmployeeServiceQueryController {

    private final EventStore eventStore;
    private final EventHelper eventHelper;
    private final QueryGateway queryGateway;


    @Override
    @GetMapping(value = "/employees/{employeeId}/events")
    public List<HistoryEventDTO> getEmployeeHistoryEvents(@PathVariable String employeeId) {
        return eventHelper.getHistoryEvents(employeeId);
    }

    @Override
    @GetMapping(value = "/employees/{registrationNumber}")
    public CompletableFuture<EmployeeDTO> getEmployeeByRegistrationNumber(@PathVariable String registrationNumber) throws EmployeeNotFoundException {
        return queryGateway.query(new EmployeeRegistrationNumberSearchQuery(registrationNumber), ResponseTypes.instanceOf(EmployeeDTO.class));
    }

    @Override
    @GetMapping(value = "/employees")
    public CompletableFuture<List<EmployeeDTO>> getAllEmployees() {
        return queryGateway.query(new AllEmployeesSearchQuery(), ResponseTypes.multipleInstancesOf(EmployeeDTO.class));
    }

    @Override
    @GetMapping(value = "/departments/{deptId}/employees")
    public CompletableFuture<List<EmployeeDTO>> getAllEmployeesByDepartment(@PathVariable String deptId) {
        return queryGateway.query(new AllEmployeesInDepartmentSearchQuery(deptId), ResponseTypes.multipleInstancesOf(EmployeeDTO.class));
    }

    @Override
    @GetMapping(value = "/departments/{deptId}/manager")
    public CompletableFuture<EmployeeDTO> getDepartmentManager(@PathVariable String deptId) throws ManagerNotFoundException {
        return queryGateway.query(new DepartmentManagerSearchQuery(deptId), ResponseTypes.instanceOf(EmployeeDTO.class));
    }
}
