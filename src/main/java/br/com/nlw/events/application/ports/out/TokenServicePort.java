package br.com.nlw.events.application.ports.out;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface TokenServicePort {
    String generateToken(Authentication authentication);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String extractUsername(String token);

    List<String> extractRoles(String token);
}