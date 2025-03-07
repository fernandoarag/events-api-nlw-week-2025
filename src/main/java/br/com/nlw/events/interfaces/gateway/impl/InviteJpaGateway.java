package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.domain.models.Invite;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.infrastructure.entity.InviteEntity;
import br.com.nlw.events.infrastructure.mapper.InviteMapper;
import br.com.nlw.events.infrastructure.repositories.InviteRepository;
import br.com.nlw.events.interfaces.gateway.database.InviteGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class InviteJpaGateway implements InviteGateway {

    private final InviteRepository inviteRepository;
    private final InviteMapper inviteMapper;

    @Override
    public Invite save(final Invite invite) {
        log.warn("InviteJpaGateway.save: {}", invite);
        final InviteEntity inviteEntity = inviteMapper.toEntity(invite);
        return inviteMapper.toDomain(inviteRepository.save(inviteEntity));
    }

    @Override
    public Invite findInviteBySubscriberId(final User subscriber) {
        return inviteMapper.toDomain(inviteRepository.findInviteEntityBySubscriberId(subscriber.getId()));
    }

    @Override
    public Invite findInviteEntityBySubscriberIdAndEventId(Long subscriberId, Long eventId) {
        final InviteEntity inviteEntity = inviteRepository.findInviteEntityBySubscriberIdAndEventId(subscriberId, eventId).orElse(null);
        return inviteMapper.toDomain(inviteEntity);
    }

    @Override
    public Long getHints(final String eventPrettyName, final Long userId) {
        return inviteRepository.getHints(eventPrettyName, userId).orElse(0L);
    }

    @Override
    @Transactional
    public void incrementHint(final Long subscriptionNumber) {
        inviteRepository.incrementHint(subscriptionNumber);
    }
}
