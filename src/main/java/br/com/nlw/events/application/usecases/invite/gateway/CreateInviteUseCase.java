package br.com.nlw.events.application.usecases.invite.gateway;

import br.com.nlw.events.domain.models.Invite;

public interface CreateInviteUseCase {
    Invite execute(final Invite invite);
}
