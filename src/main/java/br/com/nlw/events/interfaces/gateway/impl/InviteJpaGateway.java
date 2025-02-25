package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.domain.model.Invite;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.infrastructure.entity.InviteEntity;
import br.com.nlw.events.infrastructure.mapper.InviteMapper;
import br.com.nlw.events.infrastructure.repository.InviteRepository;
import br.com.nlw.events.interfaces.gateway.database.InviteGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class InviteJpaGateway implements InviteGateway {

    private final InviteRepository inviteRepository;
    private final InviteMapper inviteMapper;

    @Override
    public Invite save(final Invite invite) {
        final InviteEntity inviteEntity = inviteMapper.toEntity(invite);
        return inviteMapper.toDomain(inviteRepository.save(inviteEntity));
    }

    @Override
    public Invite findInviteBySubscriberId(final User subscriber) {
        return inviteRepository.findInviteEntityBySubscriberId(subscriber.getId());
    }

    @Override
    public Integer getHints(final String eventPrettyName, final Integer userId) {
        return inviteRepository.getHints(eventPrettyName, userId).orElse(0);
    }

    @Override
    @Transactional
    public void incrementHint(final String eventPrettyName, final Integer userId) {
        inviteRepository.incrementHint(eventPrettyName, userId);
    }
}
