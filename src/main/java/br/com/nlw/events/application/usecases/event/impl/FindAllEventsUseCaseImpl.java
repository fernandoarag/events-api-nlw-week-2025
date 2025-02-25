package br.com.nlw.events.application.usecases.event.impl;

import br.com.nlw.events.application.usecases.event.gateway.FindAllEventsUseCase;
import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllEventsUseCaseImpl implements FindAllEventsUseCase {

    private final EventGateway eventGateway;

    public FindAllEventsUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    public List<Event> execute() {
        return eventGateway.findAll();
    }
}
