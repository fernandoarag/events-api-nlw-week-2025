package br.com.nlw.events.interfaces.gateway.database;

import br.com.nlw.events.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventGateway {
    Event save(final Event event);

    List<Event> findAll();

    Optional<Event> findByPrettyName(final String prettyName);
}
