package br.com.nlw.events.application.usecases.event.gateway;

import br.com.nlw.events.domain.models.Event;

public interface FindEventByPrettyNameUseCase {
    Event execute(final String prettyName);
}
