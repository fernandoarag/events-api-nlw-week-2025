package br.com.nlw.events.application.usecases.event.gateway;

import br.com.nlw.events.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllEventsUseCase {
    //    List<Event> execute();
    Page<Event> execute(Pageable pageable);
}
