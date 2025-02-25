package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event toDomain(EventEntity entity) {
        if (entity == null) return null;
        return new Event(
                entity.getEventId(),
                entity.getTitle(),
                entity.getAbout(),
                entity.getEventType(),
                entity.getPrettyName(),
                entity.getLocation(),
                entity.getPrice(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    public EventEntity toEntity(Event event) {
        if (event == null) return null;
        return new EventEntity(
                event.getTitle(),
                event.getAbout(),
                event.getEventType(),
                event.getPrettyName(),
                event.getLocation(),
                event.getPrice(),
                event.getStartDate(),
                event.getEndDate(),
                event.getStartTime(),
                event.getEndTime()
        );
    }
}