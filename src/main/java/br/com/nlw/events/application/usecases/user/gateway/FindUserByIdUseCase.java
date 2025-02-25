package br.com.nlw.events.application.usecases.user.gateway;

import br.com.nlw.events.domain.model.User;

import java.util.UUID;

public interface FindUserByIdUseCase {
    User execute(final Integer userId);
}
