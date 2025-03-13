package br.com.nlw.events.application.usecases.event.impl;

import br.com.nlw.events.application.usecases.event.gateway.FindEventsUseCase;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.infrastructure.repositories.filter.EventFilter;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindEventsUseCaseImpl implements FindEventsUseCase {

    private final EventGateway eventGateway;

    public FindEventsUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    public Page<Event> execute(EventFilter eventFilter, Pageable pageable) {
        return eventGateway.filter(eventFilter, pageable);
    }
}
