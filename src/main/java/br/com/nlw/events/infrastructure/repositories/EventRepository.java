package br.com.nlw.events.infrastructure.repositories;

import br.com.nlw.events.infrastructure.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Optional<EventEntity> findEventEntityByPrettyName(final String prettyName);
}
