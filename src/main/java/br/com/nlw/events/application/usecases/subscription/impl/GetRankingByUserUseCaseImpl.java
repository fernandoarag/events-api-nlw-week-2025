package br.com.nlw.events.application.usecases.subscription.impl;

import br.com.nlw.events.application.exception.custom.UserIndicationNotFoundException;
import br.com.nlw.events.application.usecases.subscription.gateway.GetRankingByUserUseCase;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingByUser;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class GetRankingByUserUseCaseImpl implements GetRankingByUserUseCase {

    @Override
    public SubscriptionRankingByUser execute(final List<SubscriptionRankingItem> ranking, final Integer userId) {
        final SubscriptionRankingItem item = ranking
                .stream()
                .filter(i -> i.userId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new UserIndicationNotFoundException("There are no registrations with user indication: " + userId));

        final int position = IntStream
                .range(0, ranking.size())
                .filter(pos -> ranking.get(pos).userId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new UserIndicationNotFoundException("There are no registrations with user indication: " + userId));

        return new SubscriptionRankingByUser(position + 1, item);
    }
}
