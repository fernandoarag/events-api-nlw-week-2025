package br.com.nlw.events.application.usecases.subscription.gateway;

import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.User;

public interface FindSubscriptionByEventAndSubscriberUseCase {
    void execute(final Event event, final User existingUser);
}
