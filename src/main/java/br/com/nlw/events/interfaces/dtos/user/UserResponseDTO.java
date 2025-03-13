package br.com.nlw.events.interfaces.dtos.user;

import java.util.List;

public record UserResponseDTO(Long id, String username, String Password, String email, List<String> roles) {
}
