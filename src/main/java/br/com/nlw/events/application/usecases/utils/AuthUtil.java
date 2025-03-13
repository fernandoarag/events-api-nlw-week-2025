package br.com.nlw.events.application.usecases.utils;

import br.com.nlw.events.application.ports.out.TokenServicePort;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.interfaces.adapter.AuthAdapter;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthUtil {

    private final AuthenticationManager authenticationManager;
    private final TokenServicePort tokenServicePort;
    private final AuthAdapter authAdapter;

    public AuthUtil(
            AuthenticationManager authenticationManager,
            TokenServicePort tokenServicePort,
            AuthAdapter authAdapter
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenServicePort = tokenServicePort;
        this.authAdapter = authAdapter;
    }

    public AuthResponseDTO getAuthResponseDTO(final String username, final String password) {
        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(authToken);
        final UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        final String jwtToken = tokenServicePort.generateToken(authentication);
        final String refreshToken = tokenServicePort.generateRefreshToken(userEntity);
        return authAdapter.toAuthResponseDTO(userEntity, jwtToken, refreshToken);
    }
}
