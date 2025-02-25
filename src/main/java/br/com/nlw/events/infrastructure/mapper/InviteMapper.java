package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.domain.model.Invite;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.entity.InviteEntity;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class InviteMapper {

    public Invite toDomain(InviteEntity entity) {
        if (entity == null) return null;
        final Event event = new Event(
                entity.getEvent().getEventId(),
                entity.getEvent().getTitle(),
                entity.getEvent().getAbout(),
                entity.getEvent().getEventType(),
                entity.getEvent().getPrettyName(),
                entity.getEvent().getLocation(),
                entity.getEvent().getPrice(),
                entity.getEvent().getStartDate(),
                entity.getEvent().getEndDate(),
                entity.getEvent().getStartTime(),
                entity.getEvent().getEndTime()
        );
        final User subscriber = new User(
                entity.getSubscriber().getId(),
                entity.getSubscriber().getName(),
                entity.getSubscriber().getEmail()
        );
        return new Invite(
                entity.getInviteId(),
                entity.getHits(),
                event,
                subscriber
        );
    }

    public InviteEntity toEntity(Invite invite) {
        if (invite == null) return null;
        final EventEntity event = new EventEntity(
                invite.getEvent().getEventId(),
                invite.getEvent().getTitle(),
                invite.getEvent().getAbout(),
                invite.getEvent().getEventType(),
                invite.getEvent().getPrettyName(),
                invite.getEvent().getLocation(),
                invite.getEvent().getPrice(),
                invite.getEvent().getStartDate(),
                invite.getEvent().getEndDate(),
                invite.getEvent().getStartTime(),
                invite.getEvent().getEndTime()
        );
        final UserEntity subscriber = new UserEntity(
                invite.getSubscriber().getId(),
                invite.getSubscriber().getName(),
                invite.getSubscriber().getEmail()
        );
        return new InviteEntity(
                invite.getInviteId(),
                invite.getHits(),
                event,
                subscriber
        );
    }
}