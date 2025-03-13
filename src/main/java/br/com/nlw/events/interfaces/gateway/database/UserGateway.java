package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.models.User;

import java.util.Optional;

public interface UserGateway {
    User save(final User user);

    Optional<User> findUserByEmail(final String email);

    Optional<User> findUserByUsername(final String username);

    User findUserById(final Long id);

}
