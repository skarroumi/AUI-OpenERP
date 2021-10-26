package ma.aui.openerp.services.authentication.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.aui.openerp.commons.enums.State;
import ma.aui.openerp.commons.enums.UserRole;
import ma.aui.openerp.services.authentication.model.converters.StateConverter;
import ma.aui.openerp.services.authentication.model.converters.UserRoleConverter;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@NoArgsConstructor @AllArgsConstructor
public class UserAccountEntity {
    @Id
    @Column(length = 36)
    private String id;
    @Column(length = 5)
    private String login;
    @Column(length = 50)
    private String password;
    @Column(length = 1)
    @Convert(converter = StateConverter.class)
    private State state;
    @Column(length = 36)
    private String employeeId;
    @Column(length = 5)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;
}
