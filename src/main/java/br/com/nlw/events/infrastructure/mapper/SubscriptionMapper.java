package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.infrastructure.entity.SubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    private final EventMapper eventMapper;
    private final UserMapper userMapper;

    public SubscriptionMapper(EventMapper eventMapper, UserMapper userMapper) {
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
    }

    public Subscription toDomain(SubscriptionEntity entity) {
        if (entity == null) return null;
        return new Subscription(
                entity.getSubscriptionNumber(),
                eventMapper.toDomain(entity.getEvent()),
                userMapper.toDomain(entity.getSubscriber()),
                userMapper.toDomain(entity.getIndication())
        );
    }

    public SubscriptionEntity toEntity(Subscription subscription) {
        if (subscription == null) return null;
        return new SubscriptionEntity(
                eventMapper.toEntity(subscription.getEvent()),
                userMapper.toEntity(subscription.getSubscriber()),
                userMapper.toEntity(subscription.getIndication())
        );
    }
}