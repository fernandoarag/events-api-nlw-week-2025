package br.com.nlw.events.infrastructure.repositories;

import br.com.nlw.events.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByEmail(final String email);

    Optional<UserEntity> findUserEntityById(final Long id);

    Optional<UserEntity> findByUsername(String username);
}


