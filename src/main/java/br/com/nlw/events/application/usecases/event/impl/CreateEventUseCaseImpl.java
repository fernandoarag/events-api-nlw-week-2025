package br.com.nlw.events.application.usecases.event.impl;

import br.com.nlw.events.application.exception.custom.events.EventAlreadyExistsException;
import br.com.nlw.events.application.usecases.event.gateway.CreateEventUseCase;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventGateway eventGateway;

    public CreateEventUseCaseImpl(EventGateway eventGateway) {
        this.eventGateway = eventGateway;
    }

    @Override
    public Event execute(final Event event) {
        if (event == null) { throw new IllegalArgumentException("Event cannot be null!"); }

        // Gerando o pretty name
        event.setPrettyName(event.getTitle().toLowerCase().replaceAll("\\s", "-"));

        final Event eventByPrettyName = eventGateway.findByPrettyName(event.getPrettyName()).orElse(null);
        if (eventByPrettyName != null) {
            throw new EventAlreadyExistsException("Event with pretty name '" + event.getPrettyName() + "' already exists!");
        }

        return eventGateway.save(event);
    }
}
