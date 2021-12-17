package ma.aui.openerp.services.authentication.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.aui.openerp.commons.enums.Active;
import ma.aui.openerp.commons.enums.UserRole;
import ma.aui.openerp.services.authentication.model.converters.StateConverter;
import ma.aui.openerp.services.authentication.model.converters.UserRoleConverter;

import javax.persistence.*;

@Entity
@Table(name = "res.users")
@NoArgsConstructor @AllArgsConstructor
public class UserAccountEntity {
    @Id
    @Column(name = "account_uuid", length = 36)
    private String accountUUID;
    @Column(name = "login", length = 64)
    private String login;
    @Column(name = "password", length = 64)
    private String password;
    @Column(name = "active", length = 1)
    @Convert(converter = StateConverter.class)
    private Active active;

    @Column(name = "employee_uuid", length = 36)
    private String employeeUUID;
    @Column(name = "role", length = 5)
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;
}
