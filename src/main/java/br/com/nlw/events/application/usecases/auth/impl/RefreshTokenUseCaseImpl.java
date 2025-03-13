package br.com.nlw.events.application.usecases.auth.impl;

import br.com.nlw.events.application.exception.custom.auth.InvalidTokenException;
import br.com.nlw.events.application.ports.out.TokenServicePort;
import br.com.nlw.events.application.usecases.auth.gateway.RefreshTokenUseCase;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.infrastructure.repositories.UserRepository;
import br.com.nlw.events.interfaces.adapter.AuthAdapter;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import br.com.nlw.events.interfaces.dtos.auth.RefreshTokenRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    private final AuthAdapter authAdapter;
    private final TokenServicePort jwtService;
    private final UserRepository userRepository;

    public AuthResponseDTO execute(RefreshTokenRequestDTO request) {
        final String refreshToken = request.getRefreshToken();
        final String username = jwtService.extractUsername(refreshToken);

        if (username == null) {
            throw new InvalidTokenException("Invalid or Expired token!");
        }

        final UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!jwtService.isTokenValid(refreshToken, userEntity)) {
            throw new InvalidTokenException("Invalid or Expired refresh token!");
        }

        final List<String> roles = userEntity.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        final String accessToken = jwtService.generateToken(
                new UsernamePasswordAuthenticationToken(userEntity, roles, userEntity.getAuthorities()));

        return authAdapter.toAuthResponseDTO(userEntity, accessToken, refreshToken);
    }

}