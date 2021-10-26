package ma.aui.openerp.commons.util;

import lombok.RequiredArgsConstructor;
import ma.aui.openerp.commons.model.ActorDTO;
import ma.aui.openerp.commons.model.HistoryEventDTO;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventHelper {

    private final EventStore eventStore;

    public void dispatchEvent(Object event, ActorDTO actor) {
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("ACTOR_ID",actor.getActorId());
        map.put("ACTOR_NAME",actor.getActorName());
        MetaData metaData=new MetaData(map);
        AggregateLifecycle.apply(event, metaData);
    }

    public ActorDTO getActor(MetaData eventMetaData) {
        ActorDTO actor=new ActorDTO();
        String actorId=(String) eventMetaData.get("ACTOR_ID");
        String actorName=(String) eventMetaData.get("ACTOR_NAME");

        if (actorId==null)
            actorId="";

        if (actorName==null)
            actorName="";

        actor.setActorId(actorId);
        actor.setActorName(actorName);
        return actor;
    }

    public List<HistoryEventDTO> getHistoryEvents(String aggreagateId){
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        DomainEventStream domainEventStream = eventStore.readEvents(aggreagateId);
        return domainEventStream.asStream()
                .map((DomainEventMessage event) -> {
                    HistoryEventDTO evt=new HistoryEventDTO();
                    evt.setEventId(event.getIdentifier());
                    evt.setEventCanonicalName(event.getPayloadType().getTypeName());
                    evt.setEventName(event.getPayloadType().getSimpleName());
                    evt.setEventDescription(event.getPayloadType().getSimpleName());
                    evt.setAggregateIdentifier(event.getAggregateIdentifier());
                    evt.setTimestamp(DATE_TIME_FORMATTER.format(event.getTimestamp()));
                    evt.setPayload(event.getPayload());
                    evt.setActor(getActor(event.getMetaData()));
                    return evt;
                }).collect(Collectors.toList());
    }

}
