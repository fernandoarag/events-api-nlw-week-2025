package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserGateway {
    User save(final User user);

    Optional<User> findUserByEmail(final String email);

    User findUserById(final Integer id);

    User updateInviteLink(final User user);
}
