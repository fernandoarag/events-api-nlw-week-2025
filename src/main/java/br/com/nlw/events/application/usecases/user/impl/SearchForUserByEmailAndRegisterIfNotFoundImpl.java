package br.com.nlw.events.application.usecases.user.impl;

import br.com.nlw.events.application.usecases.user.gateway.SearchForUserByEmailAndRegisterIfNotFound;
import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class SearchForUserByEmailAndRegisterIfNotFoundImpl implements SearchForUserByEmailAndRegisterIfNotFound {

    private final UserGateway userGateway;

    public SearchForUserByEmailAndRegisterIfNotFoundImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(final User user, final Event event) {
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null!");
        }

        return userGateway.findUserByEmail(user.getEmail()).orElse(null);
    }
}
