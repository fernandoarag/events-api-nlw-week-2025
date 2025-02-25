package br.com.nlw.events.application.usecases.user.impl;

import br.com.nlw.events.application.usecases.user.gateway.FindUserByIdUseCase;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final UserGateway userGateway;

    public FindUserByIdUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(final Integer userId) {
        if (userId == null) {
            return null;
        }
        return userGateway.findUserById(userId);
    }
}
