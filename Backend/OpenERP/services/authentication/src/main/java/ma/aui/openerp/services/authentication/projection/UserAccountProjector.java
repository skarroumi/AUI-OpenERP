package ma.aui.openerp.services.authentication.projection;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.events.UserAccountCreatedEvent;
import ma.aui.openerp.services.authentication.model.UserAccountEntity;
import ma.aui.openerp.services.authentication.repository.UserAccountRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountProjector {

    private final UserAccountRepository userAccountRepository;

    @EventHandler
    public void on(UserAccountCreatedEvent event){
        UserAccountEntity entity = new UserAccountEntity(event.getId(),
                event.getLogin(),
                event.getPassword(),
                event.getState(),
                event.getEmployeeId(),
                event.getRole());
        userAccountRepository.save(entity);
    }
}
