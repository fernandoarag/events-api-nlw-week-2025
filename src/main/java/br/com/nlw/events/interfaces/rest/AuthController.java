package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.auth.AuthenticationUseCase;
import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.adapter.AuthAdapter;
import br.com.nlw.events.interfaces.dtos.auth.AuthRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import br.com.nlw.events.interfaces.dtos.auth.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationUseCase authenticationUseCase;
    private final AuthAdapter authAdapter;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        User user = authAdapter.toUser(request);
        String token = authenticationUseCase.register(user);
        return ResponseEntity.ok(AuthResponseDTO.builder().token(token).build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request) {
        String token = authenticationUseCase.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(AuthResponseDTO.builder().token(token).build());
    }
}