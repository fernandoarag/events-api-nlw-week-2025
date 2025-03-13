package br.com.nlw.events.interfaces.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private Long userId;
    private String username;
    private String email;
    private String token;
    private String refreshToken;
    private List<String> roles;
}