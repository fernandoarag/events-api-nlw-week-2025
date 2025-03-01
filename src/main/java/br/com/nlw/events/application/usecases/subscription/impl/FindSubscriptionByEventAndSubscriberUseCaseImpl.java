package br.com.nlw.events.application.usecases.subscription.impl;

import br.com.nlw.events.application.exception.custom.SubscriptionConflictException;
import br.com.nlw.events.application.usecases.subscription.gateway.FindSubscriptionByEventAndSubscriberUseCase;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.gateway.database.SubscriptionGateway;
import org.springframework.stereotype.Service;

@Service
public class FindSubscriptionByEventAndSubscriberUseCaseImpl implements FindSubscriptionByEventAndSubscriberUseCase {

    private final SubscriptionGateway subscriptionGateway;

    public FindSubscriptionByEventAndSubscriberUseCaseImpl(SubscriptionGateway subscriptionGateway) {
        this.subscriptionGateway = subscriptionGateway;
    }

    @Override
    public void execute(final Event event, final User existingUser) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        if (existingUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (subscriptionGateway.findByEventAndSubscriber(event, existingUser) != null) {
            throw new SubscriptionConflictException("There is already a registration for the user: '" + existingUser.getUsername() + "', in the event '" + event.getTitle() + "'");
        }
    }
}
