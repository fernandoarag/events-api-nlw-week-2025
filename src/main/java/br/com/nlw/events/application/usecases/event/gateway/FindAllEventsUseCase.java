package br.com.nlw.events.application.usecases.event.gateway;

import br.com.nlw.events.domain.model.Event;

import java.util.List;

public interface FindAllEventsUseCase {
    List<Event> execute();
}
