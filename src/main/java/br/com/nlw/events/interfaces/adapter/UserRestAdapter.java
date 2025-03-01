package br.com.nlw.events.interfaces.adapter;

import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.interfaces.dtos.user.UserRequestDTO;
import br.com.nlw.events.interfaces.dtos.user.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRestAdapter {

    public User toDomain(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return new User(dto.username(), dto.password(), dto.email());
    }

    public UserResponseDTO toResponse(User saved) {
        if (saved == null) {
            return null;
        }
        final List<String> roles = new ArrayList<>();
        saved.getRoles().forEach(roleEntity -> roles.add(roleEntity.getName()));
        return new UserResponseDTO(saved.getId(), saved.getUsername(), saved.getPassword(), saved.getEmail(), roles);
    }
}
