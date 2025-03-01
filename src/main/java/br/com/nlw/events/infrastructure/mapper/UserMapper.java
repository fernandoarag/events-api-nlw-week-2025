package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public User toDomain(UserEntity entity) {
        if(entity == null) return null;
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                roleMapper.toDomain(entity.getRoles())
        );
    }

    public UserEntity toEntity(User user) {
        if(user == null) return null;
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .roles(roleMapper.toEntity(user.getRoles()))
                .build();
    }
}