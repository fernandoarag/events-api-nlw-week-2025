package br.com.nlw.events.application.usecases.event.gateway;

import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.infrastructure.repositories.filter.EventFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllEventsUseCase {
    Page<Event> execute(EventFilter eventFilter, Pageable pageable);
}
