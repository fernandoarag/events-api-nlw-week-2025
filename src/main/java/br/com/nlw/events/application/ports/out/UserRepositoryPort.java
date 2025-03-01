package br.com.nlw.events.application.ports.out;

import br.com.nlw.events.domain.models.User;

import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);

    User save(User user);
}