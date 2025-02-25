package br.com.nlw.events.application.usecases.subscription.gateway;

import br.com.nlw.events.domain.model.Subscription;
import br.com.nlw.events.domain.model.User;

import java.util.UUID;

public interface CreateSubscriptionUseCase {
    Subscription execute(final String prettyName, final Integer userIndicatorId, final User userRequest);
}
