package br.com.nlw.events.application.usecases.user.gateway;

import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.naming.NoPermissionException;

public interface UpdateUserUseCase {
    @PreAuthorize("#userId == authentication.principal.id")
    User execute(final String loggedInUsername, final Long userId, final UserRequestDTO userDto)
            throws NoPermissionException;
}
