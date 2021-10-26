package ma.aui.openerp.commons.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LeaveDecisionDTOComposite {
    private final ActorDTO actor;
    private final LeaveDecisionDTO decision;
}
