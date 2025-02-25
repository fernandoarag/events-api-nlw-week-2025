package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.domain.model.User;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.infrastructure.mapper.UserMapper;
import br.com.nlw.events.infrastructure.repository.UserRepository;
import br.com.nlw.events.interfaces.gateway.database.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User save(final User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMapper.toDomain(userRepository.save(userEntity));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserEntityByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public User findUserById(final Integer id) {
        final UserEntity userEntity = userRepository.findUserEntityById(id).orElse(null);
        return userMapper.toDomain(userEntity);
    }

    @Override
    public User updateInviteLink(final User user) {
        return this.save(user);
    }
}
