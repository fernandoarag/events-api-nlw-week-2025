package br.com.nlw.events.application.usecases.invite.gateway;

import br.com.nlw.events.domain.model.Invite;

public interface CreateInviteUseCase {
    Invite execute(final Invite invite);
}
