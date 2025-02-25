package br.com.nlw.events.application.usecases.event.impl;

import br.com.nlw.events.application.exception.custom.EventNotFoundException;
import br.com.nlw.events.application.usecases.event.gateway.FindEventByPrettyNameUseCase;
import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import org.springframework.stereotype.Service;

@Service
public class FindEventByPrettyNameUseCaseImpl implements FindEventByPrettyNameUseCase {

    private final EventGateway eventGateway;

    public FindEventByPrettyNameUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    public Event execute(final String prettyName) {
        return eventGateway.findByPrettyName(prettyName)
                .orElseThrow(() -> new EventNotFoundException("Event: " + prettyName + ", does not exist!"));
    }
}
