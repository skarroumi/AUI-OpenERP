package ma.aui.openerp.services.authentication.model;

import lombok.NoArgsConstructor;
import ma.aui.openerp.commons.commands.UserAccountCreationCommand;
import ma.aui.openerp.commons.enums.State;
import ma.aui.openerp.commons.enums.UserRole;
import ma.aui.openerp.commons.events.UserAccountCreatedEvent;
import ma.aui.openerp.commons.util.EventHelper;
import ma.aui.openerp.commons.util.OpenERPHelper;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class UserAccountAggregate {
    @AggregateIdentifier
    private String id;
    private String login;
    private String password;
    private State state;
    private String employeeId;
    private UserRole role;

    @CommandHandler
    public UserAccountAggregate(UserAccountCreationCommand cmd, EventHelper eventHelper, OpenERPHelper openERPHelper){
        String aggregateId = UUID.randomUUID().toString();
        UserAccountCreatedEvent event = new UserAccountCreatedEvent(aggregateId,
                cmd.getLogin(),
                cmd.getPassword(),
                State.ENABLED,
                cmd.getEmployeeId(),
                cmd.getRole());
        eventHelper.dispatchEvent(event, cmd.getActor());
    }
    @EventSourcingHandler
    public void on(UserAccountCreatedEvent event){
        this.id = event.getId();
        this.login = event.getLogin();
        this.password = event.getPassword();
        this.state = event.getState();
        this.employeeId = event.getEmployeeId();
        this.role = event.getRole();
    }
}
