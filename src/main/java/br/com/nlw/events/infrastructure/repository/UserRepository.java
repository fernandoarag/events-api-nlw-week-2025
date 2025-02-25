package br.com.nlw.events.infrastructure.repository;

import br.com.nlw.events.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityByEmail(final String email);

    Optional<UserEntity> findUserEntityById(final Integer id);
}
