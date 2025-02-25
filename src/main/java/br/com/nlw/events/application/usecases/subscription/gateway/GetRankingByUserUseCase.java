package br.com.nlw.events.application.usecases.subscription.gateway;

import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingByUser;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;

import java.util.List;
import java.util.UUID;

public interface GetRankingByUserUseCase {
    SubscriptionRankingByUser execute(final List<SubscriptionRankingItem> ranking, final Integer userId);
}
