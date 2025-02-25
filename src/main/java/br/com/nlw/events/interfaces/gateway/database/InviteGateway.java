package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.model.Invite;
import br.com.nlw.events.domain.model.User;

public interface InviteGateway {
    Invite save(final Invite invite);

    Invite findInviteBySubscriberId(final User subscriber);

    Integer getHints(final String eventPrettyName, final Integer userId);

    void incrementHint(final String eventPrettyName, final Integer userId);
}
