package br.com.nlw.events.infrastructure.repositories.adapter;

import br.com.nlw.events.application.ports.out.UserRepositoryPort;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.infrastructure.mapper.UserMapper;
import br.com.nlw.events.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.toDomain(savedEntity);
    }
}