package br.com.nlw.events.application.usecases.invite.impl;

import br.com.nlw.events.application.usecases.invite.gateway.CreateInviteUseCase;
import br.com.nlw.events.application.usecases.invite.gateway.FindInviteBySubscriberIdAndEventIdUseCase;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.Invite;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.gateway.database.InviteGateway;
import org.springframework.stereotype.Service;

@Service
public class FindInviteBySubscriberIdAndEventIdUseCaseImpl implements FindInviteBySubscriberIdAndEventIdUseCase {

    private final InviteGateway inviteGateway;
    private final CreateInviteUseCase createInviteUseCase;

    public FindInviteBySubscriberIdAndEventIdUseCaseImpl(InviteGateway inviteGateway, CreateInviteUseCase createInviteUseCase) {
        this.inviteGateway = inviteGateway;
        this.createInviteUseCase = createInviteUseCase;
    }

    @Override
    public Invite execute(final Event event, final User existingUser) {
        final Invite existingInvite = inviteGateway.findInviteEntityBySubscriberIdAndEventId(existingUser.getId(), event.getId());
        if (existingInvite == null) {
            return createInviteUseCase.execute(new Invite(0L, event, existingUser));
        }
        return existingInvite;
    }
}
