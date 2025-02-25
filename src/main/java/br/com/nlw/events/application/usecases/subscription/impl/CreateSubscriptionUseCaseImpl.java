package br.com.nlw.events.application.usecases.subscription.impl;

import br.com.nlw.events.application.exception.custom.EventNotFoundException;
import br.com.nlw.events.application.usecases.invite.gateway.CreateInviteUseCase;
import br.com.nlw.events.application.usecases.subscription.gateway.CreateSubscriptionUseCase;
import br.com.nlw.events.application.usecases.subscription.gateway.FindSubscriptionByEventAndSubscriberUseCase;
import br.com.nlw.events.application.usecases.user.gateway.CreateUserUseCase;
import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.domain.model.Invite;
import br.com.nlw.events.domain.model.Subscription;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import br.com.nlw.events.interfaces.gateway.database.SubscriptionGateway;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CreateSubscriptionUseCaseImpl implements CreateSubscriptionUseCase {

    private final UserGateway userGateway;
    private final EventGateway eventGateway;
    private final SubscriptionGateway subscriptionGateway;

    private final CreateUserUseCase createUserUseCase;
    private final CreateInviteUseCase createInviteUseCase;
    private final FindSubscriptionByEventAndSubscriberUseCase findSubscriptionByEventAndSubscriberUseCase;

    public CreateSubscriptionUseCaseImpl(
            UserGateway userGateway,
            EventGateway eventGateway,
            SubscriptionGateway subscriptionGateway, CreateUserUseCase createUserUseCase, CreateInviteUseCase createInviteUseCase, FindSubscriptionByEventAndSubscriberUseCase findSubscriptionByEventAndSubscriberUseCase
    ) {
        this.userGateway = userGateway;
        this.eventGateway = eventGateway;
        this.subscriptionGateway = subscriptionGateway;
        this.createUserUseCase = createUserUseCase;
        this.createInviteUseCase = createInviteUseCase;
        this.findSubscriptionByEventAndSubscriberUseCase = findSubscriptionByEventAndSubscriberUseCase;
    }

    @Override
    public Subscription execute(final String prettyName, final Integer userIndicatorId, final User userRequest) {
        // Recuperar o evento pelo nome
        final Event event = eventGateway.findByPrettyName(prettyName)
                .orElseThrow(() -> new EventNotFoundException("Event: " + prettyName + ", does not exist!"));

        // Buscar usuário pelo e-mail do userRequest
        final User user = userGateway.findUserByEmail(userRequest.getEmail())
                .orElseGet(() -> {
                    // Criar novo usuário
                    final User newUser = createUserUseCase.execute(userRequest);
                    // Criar convite para o novo usuário
                    createInviteUseCase.execute(new Invite(0, event, newUser));
                    return newUser;
                });

        // Buscar usuário pelo id

//        final User indicatorUser = userIndicatorId != null
//                ? userGateway.findUserById(userIndicatorId).orElse(null)
//                : null;


        // Verifica se para o determinado evento o usuário enviado já está inscrito
        findSubscriptionByEventAndSubscriberUseCase.execute(event, user);

        log.warn("userIndicatorId -Y-Y-Y-Y-Y-Y-Y-Y-Y-Y-Y-Y-Y-Y-: {}", userIndicatorId);
        if (userIndicatorId != null) {
            final User indicatorUser = userGateway.findUserById(userIndicatorId);
            log.warn("indicatorUser -X-X-X-XX-X-X-X-X-X-X-XX-X-X-X-X: {}", indicatorUser);
            return subscriptionGateway.save(new Subscription(event, user, indicatorUser));
        }
        return subscriptionGateway.save(new Subscription(event, user, null));
    }
}
