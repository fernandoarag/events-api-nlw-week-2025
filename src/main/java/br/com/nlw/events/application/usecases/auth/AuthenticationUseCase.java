package br.com.nlw.events.application.usecases.auth;

import br.com.nlw.events.application.ports.out.TokenServicePort;
import br.com.nlw.events.application.usecases.user.gateway.CreateUserUseCase;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.infrastructure.repositories.UserRepository;
import br.com.nlw.events.interfaces.adapter.AuthAdapter;
import br.com.nlw.events.interfaces.dtos.auth.AuthRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import br.com.nlw.events.interfaces.dtos.auth.RefreshTokenRequestDTO;
import br.com.nlw.events.interfaces.dtos.auth.RegisterRequestDTO;
import br.com.nlw.events.interfaces.gateway.database.RoleGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final UserRepository userRepository;
    private final TokenServicePort jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthAdapter authAdapter;
    private final RoleGateway roleGateway;
    private final CreateUserUseCase createUserUseCase;

    public AuthResponseDTO authenticate(AuthRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        final UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        final String jwtToken = jwtService.generateToken(authentication);
        final String refreshToken = jwtService.generateRefreshToken(userEntity);

        return authAdapter.toAuthResponseDTO(userEntity, jwtToken, refreshToken);
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        final User user = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmail(),
                roleGateway.findAllById(null) // TODO: Implementar permissão para gerenciar ROLES
        );

        final User createdUser = createUserUseCase.execute(user);

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createdUser.getUsername(),
                        request.getPassword())
        );

        final UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        final String jwtToken = jwtService.generateToken(authentication);
        final String refreshToken = jwtService.generateRefreshToken(userEntity);

        // Gera um token para o novo usuário
        return authAdapter.toAuthResponseDTO(userEntity, jwtToken, refreshToken);
    }

    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO request) {
        final String refreshToken = request.getRefreshToken();
        final String username = jwtService.extractUsername(refreshToken);

        if (username == null) {
            throw new RuntimeException("Token inválido ou expirado");
        }

        final UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!jwtService.isTokenValid(refreshToken, userEntity)) {
            throw new RuntimeException("Refresh token inválido");
        }

        final List<String> roles = userEntity.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        final String accessToken = jwtService.generateToken(
                new UsernamePasswordAuthenticationToken(
                        userEntity,
                        roles,
                        userEntity.getAuthorities()
                )
        );

        return authAdapter.toAuthResponseDTO(userEntity, accessToken, refreshToken);
    }
}