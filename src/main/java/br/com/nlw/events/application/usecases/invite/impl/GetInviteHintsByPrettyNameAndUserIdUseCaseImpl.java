package br.com.nlw.events.application.usecases.invite.impl;

import br.com.nlw.events.application.usecases.invite.gateway.GetInviteHintsByPrettyNameAndUserIdUseCase;
import br.com.nlw.events.interfaces.gateway.database.InviteGateway;
import org.springframework.stereotype.Service;

@Service
public class GetInviteHintsByPrettyNameAndUserIdUseCaseImpl implements GetInviteHintsByPrettyNameAndUserIdUseCase {

    private final InviteGateway inviteGateway;

    public GetInviteHintsByPrettyNameAndUserIdUseCaseImpl(InviteGateway inviteGateway) {
        this.inviteGateway = inviteGateway;
    }

    public Long execute(final String eventPrettyName, final Long userId) {
        if (eventPrettyName == null) {
            throw new IllegalArgumentException("Event Pretty Name cannot be null!");
        }
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null!");
        }
        return inviteGateway.getHints(eventPrettyName, userId);
    }
}
