package br.com.nlw.events.application.usecases.auth.impl;

import br.com.nlw.events.application.usecases.auth.gateway.RegisterUseCase;
import br.com.nlw.events.application.usecases.user.gateway.CreateUserUseCase;
import br.com.nlw.events.application.usecases.utils.AuthUtil;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import br.com.nlw.events.interfaces.dtos.auth.RegisterRequestDTO;
import br.com.nlw.events.interfaces.gateway.database.RoleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUseCaseImpl implements RegisterUseCase {

    private final AuthUtil authUtil;
    private final RoleGateway roleGateway;
    private final PasswordEncoder passwordEncoder;
    private final CreateUserUseCase createUserUseCase;


    public AuthResponseDTO execute(RegisterRequestDTO request) {
        final String passEncoder = passwordEncoder.encode(request.getPassword());

        // TODO: Implementar permissão para gerenciar ROLES
        final User user = new User(request.getUsername(), passEncoder, request.getEmail(),
                roleGateway.findAllById(null));

        final User createdUser = createUserUseCase.execute(user);

        return authUtil.getAuthResponseDTO(createdUser.getUsername(), request.getPassword());
    }
}