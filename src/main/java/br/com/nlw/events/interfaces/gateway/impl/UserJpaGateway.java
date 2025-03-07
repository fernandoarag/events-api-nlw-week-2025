package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.domain.models.Role;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.infrastructure.entity.RoleEntity;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.infrastructure.mapper.RoleMapper;
import br.com.nlw.events.infrastructure.mapper.UserMapper;
import br.com.nlw.events.infrastructure.repositories.UserRepository;
import br.com.nlw.events.interfaces.gateway.database.RoleGateway;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleGateway roleGateway;

    @Override
    public User save(final User user) {
        final UserEntity userEntity = userMapper.toEntity(user);
        final Set<Long> rolesIds = new HashSet<>();

        if (user.getRoles() != null) {
            rolesIds.addAll(user.getRoles().stream().map(Role::getId).collect(Collectors.toSet()));
        }

        final Set<RoleEntity> savedRoles = new HashSet<>(roleMapper.toEntity(roleGateway.findAllById(rolesIds)));

        userEntity.setRoles(savedRoles);
        return userMapper.toDomain(userRepository.save(userEntity));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserEntityByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public User findUserById(final Long id) {
        if (id == null) {
            return null;
        }
        final UserEntity userEntity = userRepository.findUserEntityById(id).orElse(null);
        return userMapper.toDomain(userEntity);
    }

    @Override
    public Optional<User> findUserByUsername(final String username) {
        return userRepository.findByUsername(username).map(userMapper::toDomain);
    }

}
