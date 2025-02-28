package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EventGateway {
    Event save(final Event event);

    Page<Event> findAll(Pageable pageable);

    Optional<Event> findByPrettyName(final String prettyName);
}
