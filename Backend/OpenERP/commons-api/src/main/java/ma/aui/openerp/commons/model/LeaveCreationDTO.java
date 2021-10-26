package ma.aui.openerp.commons.model;


import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class LeaveCreationDTO {
    private final NewLeaveDTO leave;
    private final ActorDTO actor;
}
