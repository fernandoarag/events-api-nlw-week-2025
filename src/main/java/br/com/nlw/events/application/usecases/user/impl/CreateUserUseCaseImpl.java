package br.com.nlw.events.application.usecases.user.impl;

import br.com.nlw.events.application.exception.custom.users.UserAlreadyExistsException;
import br.com.nlw.events.application.exception.custom.users.UserNotFoundException;
import br.com.nlw.events.application.usecases.user.gateway.CreateUserUseCase;
import br.com.nlw.events.domain.models.User;
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
            throw new UserNotFoundException("User not found!");
        }
        userGateway.findUserByEmail(user.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException("User with email " + u.getEmail() + " already exists!");
        });
        userGateway.findUserByUsername(user.getUsername()).ifPresent(u -> {
            throw new UserAlreadyExistsException("User with username " + u.getUsername() + " already exists!");
        });

        return userGateway.save(user);
    }
}
