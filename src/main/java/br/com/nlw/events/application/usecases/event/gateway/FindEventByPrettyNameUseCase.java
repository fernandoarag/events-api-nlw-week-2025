package br.com.nlw.events.application.usecases.event.gateway;

import br.com.nlw.events.domain.model.Event;

public interface FindEventByPrettyNameUseCase {
    Event execute(final String prettyName);
}
