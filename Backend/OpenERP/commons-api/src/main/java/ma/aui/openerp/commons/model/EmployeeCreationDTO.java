package ma.aui.openerp.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class EmployeeCreationDTO implements Serializable {
    private NewEmployeeDTO employee;
    private ActorDTO actor;
}
