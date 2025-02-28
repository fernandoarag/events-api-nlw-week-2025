package br.com.nlw.events.application.usecases.event.impl;

import br.com.nlw.events.application.usecases.event.gateway.FindAllEventsUseCase;
import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllEventsUseCaseImpl implements FindAllEventsUseCase {

    private final EventGateway eventGateway;

    public FindAllEventsUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    public Page<Event> execute(Pageable pageable) {
        return eventGateway.findAll(pageable);
    }
}
