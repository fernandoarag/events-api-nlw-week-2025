package br.com.nlw.events.application.usecases.subscription.impl;

import br.com.nlw.events.application.exception.custom.EventNotFoundException;
import br.com.nlw.events.application.usecases.invite.gateway.FindInviteBySubscriberIdAndEventIdUseCase;
import br.com.nlw.events.application.usecases.subscription.gateway.CreateSubscriptionUseCase;
import br.com.nlw.events.application.usecases.subscription.gateway.FindSubscriptionByEventAndSubscriberUseCase;
import br.com.nlw.events.application.usecases.user.gateway.CreateUserUseCase;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.domain.models.Invite;
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
    private final FindInviteBySubscriberIdAndEventIdUseCase findInviteBySubscriberIdAndEventIdUseCase;

    public CreateSubscriptionUseCaseImpl(
            UserGateway userGateway,
            EventGateway eventGateway,
            CreateUserUseCase createUserUseCase,
            SubscriptionGateway subscriptionGateway,
            FindSubscriptionByEventAndSubscriberUseCase findSubscriptionByEventAndSubscriberUseCase,
            FindInviteBySubscriberIdAndEventIdUseCase findInviteBySubscriberIdAndEventIdUseCase) {
        this.userGateway = userGateway;
        this.eventGateway = eventGateway;
        this.subscriptionGateway = subscriptionGateway;
        this.createUserUseCase = createUserUseCase;
        this.findSubscriptionByEventAndSubscriberUseCase = findSubscriptionByEventAndSubscriberUseCase;
        this.findInviteBySubscriberIdAndEventIdUseCase = findInviteBySubscriberIdAndEventIdUseCase;
    }

    @Override
    public Subscription execute(final String prettyName, final Long userIndicatorId, final User userRequest) {
        // Recuperar o evento pelo nome
        final Event event = eventGateway.findByPrettyName(prettyName)
                .orElseThrow(() -> new EventNotFoundException("Event: " + prettyName + ", does not exist!"));

        // Buscar usuário pelo e-mail do userRequest
        final User user = userGateway.findUserByEmail(userRequest.getEmail())
                .orElse(createUserUseCase.execute(userRequest)); // Criar novo usuário

        log.warn("Criar novo usuário {}", user.toString());

        // Criar convite para o novo usuário
        final Invite invite = findInviteBySubscriberIdAndEventIdUseCase.execute(event, user);
        log.warn("createInviteUseCase {}", invite.toString());

        // Verifica se para o determinado evento o usuário enviado já está inscrito
        findSubscriptionByEventAndSubscriberUseCase.execute(event, user);

        // Buscar usuário de indicação pelo id
        final User indicatorUser = userGateway.findUserById(userIndicatorId);

        return subscriptionGateway.saveSubscriptionWithEvent(new Subscription(event, user, indicatorUser));
    }
}
