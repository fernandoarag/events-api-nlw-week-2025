package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.domain.model.Subscription;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;

import java.util.List;
import java.util.UUID;

public interface SubscriptionGateway {
    Subscription save(final Subscription subscription);

    Subscription findByEventAndSubscriber(final Event event, final User existingUser);

    List<SubscriptionRankingItem> getCompleteRanking(final Integer eventId);
}
