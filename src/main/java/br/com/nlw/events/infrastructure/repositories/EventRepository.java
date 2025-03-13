package br.com.nlw.events.infrastructure.repositories;

import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.repositories.filter.EventRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long>, EventRepositoryQuery {
    Optional<EventEntity> findEventEntityByPrettyName(final String prettyName);
}
