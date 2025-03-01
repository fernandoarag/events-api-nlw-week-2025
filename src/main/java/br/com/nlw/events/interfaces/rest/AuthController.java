package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.auth.AuthenticationUseCase;
import br.com.nlw.events.application.usecases.user.gateway.FindUserByUsernameUseCase;
import br.com.nlw.events.application.usecases.user.gateway.UpdateUserUseCase;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.adapter.UserRestAdapter;
import br.com.nlw.events.interfaces.dtos.auth.AuthRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import br.com.nlw.events.interfaces.dtos.auth.RefreshTokenRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.RegisterRequestDTO;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import br.com.nlw.events.interfaces.dtos.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;
    private final UserRestAdapter userRestAdapter;
    private final UpdateUserUseCase updateUserUseCase;
    private final FindUserByUsernameUseCase findUserByUsernameUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        AuthResponseDTO authResponseDTO = authenticationUseCase.register(request);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO request) {
        AuthResponseDTO authResponseDTO = authenticationUseCase.refreshToken(request);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
        AuthResponseDTO authResponseDTO = authenticationUseCase.authenticate(request);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @RequestBody UserRequestDTO userDto,
                                                      Principal principal) throws NoPermissionException {
        // Obtendo o usuário autenticado
        final String loggedInUsername = principal.getName();
        final User authenticatedUser = findUserByUsernameUseCase.execute(loggedInUsername);

        // Verificando se o usuário pode alterar apenas os próprios dados
        if (!authenticatedUser.getId().equals(id)) {
            throw new NoPermissionException("Você não tem permissão para alterar os dados de outro usuário");
        }

        // Atualizando os dados do usuário
        final User userRequest = userRestAdapter.toDomain(userDto);
        final User updatedUser = updateUserUseCase.execute(id, userRequest);
        return ResponseEntity.ok(userRestAdapter.toResponse(updatedUser));
    }

}