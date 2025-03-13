package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.application.exception.custom.events.EventNotFoundException;
import br.com.nlw.events.application.exception.custom.users.UserIndicationNotFoundException;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.entity.SubscriptionEntity;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.infrastructure.mapper.SubscriptionMapper;
import br.com.nlw.events.infrastructure.repositories.EventRepository;
import br.com.nlw.events.infrastructure.repositories.SubscriptionRepository;
import br.com.nlw.events.infrastructure.repositories.UserRepository;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;
import br.com.nlw.events.interfaces.gateway.database.SubscriptionGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionJpaGateway implements SubscriptionGateway {

    private static final String DOES_NOT_EXIST = ", does not exist!";

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public Subscription save(final Subscription subscription) {
        final SubscriptionEntity subscriptionEntity = subscriptionMapper.toEntity(subscription);
        final EventEntity eventEntity = subscriptionEntity.getEvent();

        if (eventEntity != null && eventEntity.getId() == null) {
            subscriptionEntity.setEvent(eventRepository.save(eventEntity));
        }

        return subscriptionMapper.toDomain(subscriptionRepository.save(subscriptionEntity));
    }

    @Override
    public Subscription findByEventAndSubscriber(final Event event, final User existingUser) {
        final EventEntity eventEntity = eventRepository.findById(event.getId())
                .orElseThrow(() -> new EventNotFoundException("Event: " + event.getPrettyName() + DOES_NOT_EXIST));

        final UserEntity existingUserEntity = userRepository.findById(existingUser.getId())
                .orElseThrow(() -> new UserIndicationNotFoundException("User: " + existingUser.getUsername() + DOES_NOT_EXIST));

        return subscriptionRepository.findByEventAndSubscriber(eventEntity, existingUserEntity)
                .map(subscriptionMapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<SubscriptionRankingItem> getCompleteRanking(final Long eventId) {
        return subscriptionRepository.generateRanking(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event: " + eventId + DOES_NOT_EXIST));
    }
}
