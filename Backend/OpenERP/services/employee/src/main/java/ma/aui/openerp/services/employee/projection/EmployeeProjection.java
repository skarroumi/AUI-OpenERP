package ma.aui.openerp.services.employee.projection;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.exceptions.EmployeeNotFoundException;
import ma.aui.openerp.commons.exceptions.ManagerNotFoundException;
import ma.aui.openerp.commons.model.EmployeeDTO;
import ma.aui.openerp.commons.queries.AllEmployeesInDepartmentSearchQuery;
import ma.aui.openerp.commons.queries.AllEmployeesSearchQuery;
import ma.aui.openerp.commons.queries.DepartmentManagerSearchQuery;
import ma.aui.openerp.commons.queries.EmployeeRegistrationNumberSearchQuery;
import ma.aui.openerp.services.employee.mappers.ModelMapper;
import ma.aui.openerp.services.employee.model.EmployeeEntity;
import ma.aui.openerp.services.employee.repository.EmployeeRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @QueryHandler
    public EmployeeDTO handle(EmployeeRegistrationNumberSearchQuery query) throws EmployeeNotFoundException{
        EmployeeEntity emp = Optional.ofNullable(employeeRepository.findByRegistrationNumber(query.getRegistrationNumber()))
                            .orElseThrow(() -> new EmployeeNotFoundException("No employee associated with " + query.getRegistrationNumber()));

        EmployeeDTO employeeDTO = modelMapper.convert(emp);
        return employeeDTO;
    }

    @QueryHandler
    public List<EmployeeDTO> handle(AllEmployeesSearchQuery query){
        List<EmployeeEntity> employees = employeeRepository.findAll();

        return employees.stream().map((EmployeeEntity entity) -> {
            return modelMapper.convert(entity);
        }).collect(Collectors.toList());
    }

    @QueryHandler
    public List<EmployeeDTO> handle(AllEmployeesInDepartmentSearchQuery query){
        List<EmployeeEntity> employees = employeeRepository.findEmployeesByDepartment(query.getDepartmentId());
        return employees.stream().map((EmployeeEntity entity) -> {
            return modelMapper.convert(entity);
        }).collect(Collectors.toList());
    }

    @QueryHandler
    public EmployeeDTO handle(DepartmentManagerSearchQuery query) throws ManagerNotFoundException {
        EmployeeEntity manager = Optional.ofNullable(employeeRepository.findDepartmentManager(query.getDepartmentId()))
                .orElseThrow(()->new ManagerNotFoundException("No manager Found for Department "+ query.getDepartmentId()+"!"));
        EmployeeDTO employeeDTO = modelMapper.convert(manager);
        return employeeDTO;
    }

}
