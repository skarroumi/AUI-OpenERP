package ma.aui.openerp.commons.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.UserRole;
import ma.aui.openerp.commons.model.ActorDTO;

@RequiredArgsConstructor
@Getter
public class UserAccountCreationCommand{
    private final String login;
    private final String password;
    private final String employeeUUID;
    private final UserRole role;
    private final ActorDTO actor;
}
