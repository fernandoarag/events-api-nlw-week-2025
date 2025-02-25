package br.com.nlw.events.application.usecases.subscription.impl;

import br.com.nlw.events.application.exception.custom.EventNotFoundException;
import br.com.nlw.events.application.usecases.subscription.gateway.GetCompleteSubscriptionRankingByPrettyNameUseCase;
import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import br.com.nlw.events.interfaces.gateway.database.SubscriptionGateway;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCompleteSubscriptionRankingByPrettyNameUseCaseImpl implements GetCompleteSubscriptionRankingByPrettyNameUseCase {

    private final SubscriptionGateway subscriptionGateway;
    private final EventGateway eventGateway;

    public GetCompleteSubscriptionRankingByPrettyNameUseCaseImpl(
            SubscriptionGateway subscriptionGateway,
            EventGateway eventGateway
    ) {
        this.eventGateway = eventGateway;
        this.subscriptionGateway = subscriptionGateway;
    }

    @Override
    public List<SubscriptionRankingItem> execute(String prettyName) {
        if (StringUtils.isBlank(prettyName)) {
            throw new IllegalArgumentException("Pretty name cannot be null or empty");
        }

        final Event event = eventGateway.findByPrettyName(prettyName)
                .orElseThrow(() -> new EventNotFoundException("Event: " + prettyName + ", does not exist!"));

        return subscriptionGateway.getCompleteRanking(event.getEventId());
    }
}
