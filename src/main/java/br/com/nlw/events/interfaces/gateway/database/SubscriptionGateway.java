package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;

import java.util.List;

public interface SubscriptionGateway {
    Subscription save(final Subscription subscription);

    Subscription findByEventAndSubscriber(final Event event, final User existingUser);

    List<SubscriptionRankingItem> getCompleteRanking(final Long eventId);
}
