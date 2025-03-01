package br.com.nlw.events.application.usecases.event.gateway;

import br.com.nlw.events.domain.models.Event;

public interface CreateEventUseCase {
    Event execute(final Event event);
}
