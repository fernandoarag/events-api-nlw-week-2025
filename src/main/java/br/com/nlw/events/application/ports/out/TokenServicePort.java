package br.com.nlw.events.application.ports.out;

import java.util.Map;

public interface TokenServicePort {
    String generateToken(String username);

    String generateToken(String username, Map<String, Object> extraClaims);

    boolean isTokenValid(String token, String username);

    String extractUsername(String token);
}