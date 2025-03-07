package br.com.nlw.events.application.usecases.invite.impl;

import br.com.nlw.events.application.usecases.invite.gateway.GetInviteHintsByPrettyNameAndUserIdUseCase;
import br.com.nlw.events.application.usecases.invite.gateway.IncrementInviteHintByPrettyNameAndUserIdUseCase;
import br.com.nlw.events.interfaces.gateway.database.InviteGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IncrementInviteHintByPrettyNameAndUserIdUseCaseImpl implements IncrementInviteHintByPrettyNameAndUserIdUseCase {

    private final InviteGateway inviteGateway;

    public IncrementInviteHintByPrettyNameAndUserIdUseCaseImpl(InviteGateway inviteGateway) {
        this.inviteGateway = inviteGateway;
    }

    public void execute(final Long subscriptionNumber) {
        if (subscriptionNumber == null) {
            throw new IllegalArgumentException("userId cannot be null!");
        }
        inviteGateway.incrementHint(subscriptionNumber);
    }
}
