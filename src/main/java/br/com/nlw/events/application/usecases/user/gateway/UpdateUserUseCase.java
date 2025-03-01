package br.com.nlw.events.application.usecases.user.gateway;

import br.com.nlw.events.domain.models.User;
import org.springframework.security.access.prepost.PreAuthorize;

public interface UpdateUserUseCase {
    @PreAuthorize("#id == authentication.principal.id")
    User execute(final Long id, final User requestUser);
}
