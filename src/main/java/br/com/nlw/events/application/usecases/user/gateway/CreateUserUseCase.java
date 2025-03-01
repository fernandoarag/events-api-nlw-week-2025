package br.com.nlw.events.application.usecases.user.gateway;

import br.com.nlw.events.domain.models.User;

public interface CreateUserUseCase {
    User execute(final User user);
}
