package br.com.nlw.events.application.usecases.user.impl;

import br.com.nlw.events.application.usecases.user.gateway.FindUserByUsernameUseCase;
import br.com.nlw.events.application.usecases.user.gateway.UpdateUserUseCase;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.adapter.UserRestAdapter;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;
    private final UserRestAdapter userRestAdapter;

    public UpdateUserUseCaseImpl(
            UserGateway userGateway,
            PasswordEncoder passwordEncoder,
            FindUserByUsernameUseCase findUserByUsernameUseCase,
            UserRestAdapter userRestAdapter) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
        this.findUserByUsernameUseCase = findUserByUsernameUseCase;
        this.userRestAdapter = userRestAdapter;
    }

    @PreAuthorize("#userId == authentication.principal.id")
    @Override
    public User execute(
            final String loggedInUsername,
            final Long userId,
            final UserRequestDTO userDto
    ) throws NoPermissionException {
        final User authenticatedUser = findUserByUsernameUseCase.execute(loggedInUsername);
        // Verificando se o usuário pode alterar apenas os próprios dados
        if (!authenticatedUser.getId().equals(userId)) {
            throw new NoPermissionException("Você não tem permissão para alterar os dados de outro usuário");
        }
        // Obtendo o usuário autenticado
        final User userRequest = userRestAdapter.toDomain(userDto);
        // Atualizando os dados do usuário
        final User updatedUser = userGateway.findUserById(userId);
        updatedUser.setUsername(userRequest.getUsername());
        updatedUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        updatedUser.setEmail(userRequest.getEmail());
        return userGateway.save(updatedUser);
    }

}
