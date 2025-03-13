package br.com.nlw.events.application.usecases.subscription.gateway;

import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.domain.models.User;

public interface CreateSubscriptionUseCase {
    Subscription execute(final String prettyName, final Long userIndicatorId, final User userRequest);
}
