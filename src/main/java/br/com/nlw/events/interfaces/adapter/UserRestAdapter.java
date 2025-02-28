package br.com.nlw.events.interfaces.adapter;

import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import br.com.nlw.events.interfaces.dtos.user.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRestAdapter {

    public User toDomain(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return new User(dto.name(), dto.email());
    }

    public UserResponseDTO toResponse(User saved) {
        if (saved == null) {
            return null;
        }
        return new UserResponseDTO(saved.getId(), saved.getUsername(), saved.getEmail());
    }
}
