package ma.aui.openerp.commons.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.enums.State;
import ma.aui.openerp.commons.enums.UserRole;

@RequiredArgsConstructor
@Getter
public class UserAccountCreatedEvent {
    private final String id;
    private final String login;
    private final String password;
    private final State state;
    private final String employeeId;
    private final UserRole role;
}
