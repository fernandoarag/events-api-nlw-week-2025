package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.application.exception.custom.EventNotFoundException;
import br.com.nlw.events.application.exception.custom.UserIndicationNotFoundException;
import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.domain.model.Subscription;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.entity.SubscriptionEntity;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.infrastructure.mapper.SubscriptionMapper;
import br.com.nlw.events.infrastructure.repository.EventRepository;
import br.com.nlw.events.infrastructure.repository.SubscriptionRepository;
import br.com.nlw.events.infrastructure.repository.UserRepository;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;
import br.com.nlw.events.interfaces.gateway.database.SubscriptionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubscriptionJpaGateway implements SubscriptionGateway {

    private static final String DOES_NOT_EXIST = ", does not exist!";

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription save(final Subscription subscription) {
        final SubscriptionEntity subscriptionEntity = subscriptionMapper.toEntity(subscription);
        return subscriptionMapper.toDomain(subscriptionRepository.save(subscriptionEntity));
    }

    @Override
    public Subscription findByEventAndSubscriber(final Event event, final User existingUser) {
        final EventEntity eventEntity = eventRepository.findById(event.getEventId())
                .orElseThrow(() -> new EventNotFoundException("Event: " + event.getPrettyName() + DOES_NOT_EXIST));

        final UserEntity existingUserEntity = userRepository.findById(existingUser.getId())
                .orElseThrow(() -> new UserIndicationNotFoundException("User: " + existingUser.getUsername() + DOES_NOT_EXIST));

        return subscriptionRepository.findByEventAndSubscriber(eventEntity, existingUserEntity)
                .map(subscriptionMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<SubscriptionRankingItem> getCompleteRanking(final Integer eventId) {
        return subscriptionRepository.generateRanking(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event: " + eventId + DOES_NOT_EXIST));
    }
}
