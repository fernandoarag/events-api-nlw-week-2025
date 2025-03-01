package br.com.nlw.events.application.usecases.user.impl;

import br.com.nlw.events.application.usecases.user.gateway.UpdateUserUseCase;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserUseCaseImpl(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("#id == authentication.principal.id")
    @Override
    public User execute(final Long id, final User requestUser) {
        // Obtendo o usuário autenticado
        final User user = userGateway.findUserById(id);
        user.setUsername(requestUser.getUsername());
        user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
        user.setEmail(requestUser.getEmail());
        return userGateway.save(user);
    }

}
