package br.com.nlw.events.application.usecases.user.impl;

import br.com.nlw.events.application.usecases.user.gateway.CreateUserUseCase;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
        userGateway.findUserByEmail(user.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("User with email " + u.getEmail() + " already exists!");
        });
        return userGateway.save(user);
    }
}
