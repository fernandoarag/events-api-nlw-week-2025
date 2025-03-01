package br.com.nlw.events.application.usecases.invite.impl;

import br.com.nlw.events.application.usecases.invite.gateway.GetInviteHintsByPrettyNameAndUserIdUseCase;
import br.com.nlw.events.application.usecases.invite.gateway.IncrementInviteHintByPrettyNameAndUserIdUseCase;
import br.com.nlw.events.interfaces.gateway.database.InviteGateway;
import org.springframework.stereotype.Service;

@Service
public class IncrementInviteHintByPrettyNameAndUserIdUseCaseImpl implements IncrementInviteHintByPrettyNameAndUserIdUseCase {

    private final InviteGateway inviteGateway;

    public IncrementInviteHintByPrettyNameAndUserIdUseCaseImpl(InviteGateway inviteGateway) {
        this.inviteGateway = inviteGateway;
    }

    public void execute(final String eventPrettyName, final Long userId) {
        if (eventPrettyName == null) {
            throw new IllegalArgumentException("Event Pretty Name cannot be null!");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null!");
        }
        inviteGateway.incrementHint(eventPrettyName, userId);
    }
}
