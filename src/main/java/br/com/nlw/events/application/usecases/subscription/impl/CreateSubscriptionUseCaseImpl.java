package br.com.nlw.events.application.usecases.subscription.impl;

import br.com.nlw.events.application.exception.custom.events.EventNotFoundException;
import br.com.nlw.events.application.usecases.subscription.gateway.CreateSubscriptionUseCase;
import br.com.nlw.events.application.usecases.subscription.gateway.FindSubscriptionByEventAndSubscriberUseCase;
import br.com.nlw.events.application.usecases.user.gateway.CreateUserUseCase;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import br.com.nlw.events.interfaces.gateway.database.SubscriptionGateway;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateSubscriptionUseCaseImpl implements CreateSubscriptionUseCase {

    private final UserGateway userGateway;
    private final EventGateway eventGateway;
    private final SubscriptionGateway subscriptionGateway;

    private final CreateUserUseCase createUserUseCase;
    private final FindSubscriptionByEventAndSubscriberUseCase findSubscriptionByEventAndSubscriberUseCase;

    public CreateSubscriptionUseCaseImpl(
            UserGateway userGateway,
            EventGateway eventGateway,
            SubscriptionGateway subscriptionGateway,
            CreateUserUseCase createUserUseCase,
            FindSubscriptionByEventAndSubscriberUseCase findSubscriptionByEventAndSubscriberUseCase
    ) {
        this.userGateway = userGateway;
        this.eventGateway = eventGateway;
        this.subscriptionGateway = subscriptionGateway;
        this.createUserUseCase = createUserUseCase;
        this.findSubscriptionByEventAndSubscriberUseCase = findSubscriptionByEventAndSubscriberUseCase;
    }

    @Override
    public Subscription execute(final String prettyName, final Long userIndicatorId, final User userRequest) {
        // Recuperar o evento pelo nome
        final Event event = eventGateway.findByPrettyName(prettyName)
                .orElseThrow(() -> new EventNotFoundException("Event: " + prettyName + ", does not exist!"));

        // Buscar usuário pelo e-mail do userRequest
        final User user = userGateway.findUserByEmail(userRequest.getEmail())
                .orElse(createUserUseCase.execute(userRequest)); // Criar novo usuário

        // Verifica se para o determinado evento o usuário enviado já está inscrito
        findSubscriptionByEventAndSubscriberUseCase.execute(event, user);

        // Buscar usuário de indicação pelo id
        final User indicatorUser = userGateway.findUserById(userIndicatorId);

        return subscriptionGateway.save(new Subscription(event, user, indicatorUser));
    }
}
