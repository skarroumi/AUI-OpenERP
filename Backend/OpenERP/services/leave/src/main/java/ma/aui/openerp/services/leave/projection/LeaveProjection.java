package ma.aui.openerp.services.leave.projection;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.model.LeaveDTO;
import ma.aui.openerp.commons.queries.AllLeavesForEmployeeSearchQuery;
import ma.aui.openerp.commons.queries.LeaveForManagerSearchQuery;
import ma.aui.openerp.commons.queries.LeaveInProgressForManagerSearchQuery;
import ma.aui.openerp.services.leave.mappers.ModelMapper;
import ma.aui.openerp.services.leave.model.LeaveEntity;
import ma.aui.openerp.services.leave.repository.LeaveRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LeaveProjection {

    private final LeaveRepository leaveRepository;
    private final ModelMapper modelMapper;

    @QueryHandler
    public List<LeaveDTO> handle(LeaveInProgressForManagerSearchQuery query){
        List<LeaveEntity> leaveInProgress = leaveRepository.getLeaveInProgress(query.getManagerIdentificationId());

        return leaveInProgress.stream().map((LeaveEntity entity) -> {
            return modelMapper.convert(entity);
        }).collect(Collectors.toList());
    }

    @QueryHandler
    public List<LeaveDTO> handle(LeaveForManagerSearchQuery query){
        List<LeaveEntity> allLeaveForManager = leaveRepository.getAllLeavesForManager(query.getManagerIdentificationId());

        return allLeaveForManager.stream().map((LeaveEntity entity) -> {
            return modelMapper.convert(entity);
        }).collect(Collectors.toList());
    }

    @QueryHandler
    public List<LeaveDTO> handle(AllLeavesForEmployeeSearchQuery query){
        List<LeaveEntity> allEmployeeLeaves = leaveRepository.getAllLeavesForEmployee(query.getIdentificationId());

        return allEmployeeLeaves.stream().map((LeaveEntity entity) -> {
            return modelMapper.convert(entity);
        }).collect(Collectors.toList());
    }
}
