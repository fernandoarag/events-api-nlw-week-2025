package br.com.nlw.events.interfaces.adapter;

import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.dtos.auth.RegisterRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthAdapter {

    public User toUser(RegisterRequestDTO registerRequestDTO) {
        return User.builder()
                .username(registerRequestDTO.getUsername())
                .email(registerRequestDTO.getEmail())
                .password(registerRequestDTO.getPassword())
                .build();
    }
}