package br.com.nlw.events.interfaces.adapter;

import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.interfaces.dtos.auth.AuthResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthAdapter {

    public AuthResponseDTO toAuthResponseDTO(UserEntity user, String jwtToken, String refreshToken) {
        final List<String> roles = new ArrayList<>();
        user.getRoles().forEach(roleEntity -> roles.add("ROLE_" + roleEntity.getName()));
        return AuthResponseDTO.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles)
                .build();
    }
}