package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.models.Invite;
import br.com.nlw.events.domain.models.User;

public interface InviteGateway {
    Invite save(final Invite invite);

    Invite findInviteBySubscriberId(final User subscriber);

    Invite findInviteEntityBySubscriberIdAndEventId(Long subscriberId, Long eventId);

    Long getHints(final String eventPrettyName, final Long userId);

    void incrementHint(final Long subscriptionNumber);
}
