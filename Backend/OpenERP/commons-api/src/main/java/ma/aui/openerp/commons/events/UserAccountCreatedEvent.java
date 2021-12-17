package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.Active;
import ma.aui.openerp.commons.enums.UserRole;

@RequiredArgsConstructor
@Getter
public class UserAccountCreatedEvent {
    private final String accountUUID;
    private final String login;
    private final String password;
    private final Active active;
    private final String employeeUUID;
    private final UserRole role;
}
