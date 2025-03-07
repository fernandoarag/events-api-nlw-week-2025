package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.infrastructure.repositories.filter.EventFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EventGateway {
    Event save(final Event event);

    Page<Event> findAll(Pageable pageable);

    Optional<Event> findByPrettyName(final String prettyName);

    Page<Event> filter(EventFilter eventFilter, Pageable pageable);
}
