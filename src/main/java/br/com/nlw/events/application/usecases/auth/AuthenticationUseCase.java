package br.com.nlw.events.application.usecases.auth;

import br.com.nlw.events.application.ports.out.TokenServicePort;
import br.com.nlw.events.application.ports.out.UserRepositoryPort;
import br.com.nlw.events.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final UserRepositoryPort userRepository;
    private final TokenServicePort tokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String authenticate(String username, String password) {
        // Autentica o usuário usando o AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Se a autenticação foi bem-sucedida, gera um token
        return tokenService.generateToken(username);
    }

    public String register(User user) {
        // Cria um novo usuário no sistema
        User savedUser = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(savedUser);

        // Gera um token para o novo usuário
        return tokenService.generateToken(user.getUsername());
    }
}