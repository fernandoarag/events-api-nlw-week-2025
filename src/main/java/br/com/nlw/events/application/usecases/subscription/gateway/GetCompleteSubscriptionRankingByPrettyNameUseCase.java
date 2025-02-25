package br.com.nlw.events.application.usecases.subscription.gateway;

import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;

import java.util.List;

public interface GetCompleteSubscriptionRankingByPrettyNameUseCase {
    List<SubscriptionRankingItem> execute(final String prettyName);
}
