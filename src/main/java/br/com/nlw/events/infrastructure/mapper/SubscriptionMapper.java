package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.domain.model.Subscription;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.entity.SubscriptionEntity;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public Subscription toDomain(SubscriptionEntity entity) {
        if (entity == null) return null;
        final User subscriber = entity.getSubscriber() != null
                ? new User(
                entity.getSubscriber().getId(),
                entity.getSubscriber().getUsername(),
                entity.getSubscriber().getEmail(),
                entity.getSubscriber().getPassword(),
                entity.getSubscriber().getRole())
                : null;
        final User indication = entity.getIndication() != null
                ? new User(
                entity.getIndication().getId(),
                entity.getIndication().getUsername(),
                entity.getIndication().getEmail(),
                entity.getIndication().getPassword(),
                entity.getIndication().getRole())
                : null;
        return new Subscription(
                entity.getSubscriptionNumber(),
                new Event(
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
                ),
                subscriber,
                indication
        );
    }

    public SubscriptionEntity toEntity(Subscription subscription) {
        if (subscription == null) return null;
        final UserEntity subscriber = subscription.getSubscriber() != null
                ? new UserEntity(
                subscription.getSubscriber().getId(),
                subscription.getSubscriber().getUsername(),
                subscription.getSubscriber().getEmail(),
                subscription.getSubscriber().getPassword(),
                subscription.getSubscriber().getRole())
                : null;
        final UserEntity indication = subscription.getIndication() != null
                ? new UserEntity(
                subscription.getIndication().getId(),
                subscription.getIndication().getUsername(),
                subscription.getIndication().getEmail(),
                subscription.getIndication().getPassword(),
                subscription.getIndication().getRole())
                : null;
        return new SubscriptionEntity(
                new EventEntity(
                        subscription.getEvent().getEventId(),
                        subscription.getEvent().getTitle(),
                        subscription.getEvent().getAbout(),
                        subscription.getEvent().getEventType(),
                        subscription.getEvent().getPrettyName(),
                        subscription.getEvent().getLocation(),
                        subscription.getEvent().getPrice(),
                        subscription.getEvent().getStartDate(),
                        subscription.getEvent().getEndDate(),
                        subscription.getEvent().getStartTime(),
                        subscription.getEvent().getEndTime()
                ),
                subscriber,
                indication
        );
    }
}