package br.com.nlw.events.application.usecases.auth.impl;

import br.com.nlw.events.application.exception.custom.users.UserNotFoundException;
import br.com.nlw.events.application.usecases.auth.gateway.AuthenticationUseCase;
import br.com.nlw.events.application.usecases.utils.AuthUtil;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.dtos.auth.AuthRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final AuthUtil authUtil;
    private final UserGateway userGateway;

    public AuthResponseDTO execute(AuthRequestDTO request) {
        final User currentUser = userGateway.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        return authUtil.getAuthResponseDTO(currentUser.getUsername(), request.getPassword());
    }
}