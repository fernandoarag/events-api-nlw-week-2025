package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }

    public UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}