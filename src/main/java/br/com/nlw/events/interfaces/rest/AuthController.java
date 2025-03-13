package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.auth.gateway.AuthenticationUseCase;
import br.com.nlw.events.application.usecases.auth.gateway.RefreshTokenUseCase;
import br.com.nlw.events.application.usecases.auth.gateway.RegisterUseCase;
import br.com.nlw.events.application.usecases.user.gateway.UpdateUserUseCase;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.adapter.UserRestAdapter;
import br.com.nlw.events.interfaces.dtos.auth.AuthRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import br.com.nlw.events.interfaces.dtos.auth.RefreshTokenRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.RegisterRequestDTO;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import br.com.nlw.events.interfaces.dtos.user.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.security.Principal;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRestAdapter userRestAdapter;
    private final RegisterUseCase registerUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        final AuthResponseDTO authResponseDTO = registerUseCase.execute(request);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO request) {
        final AuthResponseDTO authResponseDTO = refreshTokenUseCase.execute(request);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody @Valid AuthRequestDTO request) {
        final AuthResponseDTO authResponseDTO = authenticationUseCase.execute(request);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(
            Principal principal,
            @PathVariable Long userId,
            @RequestBody UserRequestDTO userDto
    ) throws NoPermissionException {
        final String loggedInUsername = principal.getName();
        final User updatedUser = updateUserUseCase.execute(loggedInUsername, userId, userDto);
        return ResponseEntity.ok(userRestAdapter.toResponse(updatedUser));
    }

}