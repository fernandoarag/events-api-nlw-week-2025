package br.com.nlw.events.application.usecases.user.gateway;

import br.com.nlw.events.domain.models.User;

public interface FindUserByIdUseCase {
    User execute(final Long userId);
}
