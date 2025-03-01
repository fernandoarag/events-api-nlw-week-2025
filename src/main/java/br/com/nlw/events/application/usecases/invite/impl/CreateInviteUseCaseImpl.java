package br.com.nlw.events.application.usecases.invite.impl;

import br.com.nlw.events.application.usecases.invite.gateway.CreateInviteUseCase;
import br.com.nlw.events.domain.models.Invite;
import br.com.nlw.events.interfaces.gateway.database.InviteGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateInviteUseCaseImpl implements CreateInviteUseCase {

    private final InviteGateway inviteGateway;

    public CreateInviteUseCaseImpl(InviteGateway inviteGateway) {
        this.inviteGateway = inviteGateway;
    }

    @Override
    public Invite execute(final Invite invite) {
        if (invite == null) {
            throw new IllegalArgumentException("Invite cannot be null!");
        }
        if (invite.getEvent() == null) {
            throw new IllegalArgumentException("Event cannot be null!");
        }
        if (invite.getEvent().getId() == null) {
            throw new IllegalArgumentException("EventId cannot be null!");
        }
        if (invite.getSubscriber() == null) {
            throw new IllegalArgumentException("Subscriber cannot be null!");
        }
        return inviteGateway.save(invite);
    }
}
