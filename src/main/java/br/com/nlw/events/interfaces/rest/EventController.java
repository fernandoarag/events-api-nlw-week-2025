package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.event.gateway.CreateEventUseCase;
import br.com.nlw.events.application.usecases.event.gateway.FindAllEventsUseCase;
import br.com.nlw.events.application.usecases.event.gateway.FindEventByPrettyNameUseCase;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.infrastructure.repositories.filter.EventFilter;
import br.com.nlw.events.interfaces.dtos.event.EventResponseDTO;
import br.com.nlw.events.interfaces.dtos.event.SortTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/events")
public class EventController {

    private final FindAllEventsUseCase findAllEventsUseCase;
    private final CreateEventUseCase createEventUseCase;
    private final FindEventByPrettyNameUseCase findEventByPrettyNameUseCase;

    public EventController(FindAllEventsUseCase findAllEventsUseCase, CreateEventUseCase createEventUseCase, FindEventByPrettyNameUseCase findEventByPrettyNameUseCase) {
        this.findAllEventsUseCase = findAllEventsUseCase;
        this.createEventUseCase = createEventUseCase;
        this.findEventByPrettyNameUseCase = findEventByPrettyNameUseCase;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Event> addNewEvent(@RequestBody final Event event) {
        return ResponseEntity.ok(createEventUseCase.execute(event));
    }

    @GetMapping()
    public EventResponseDTO getAllEvents(final EventFilter eventFilter) {
        log.error("EventFilter: {}", eventFilter);
        eventFilter.setPage(eventFilter.getPage() > 0 ? eventFilter.getPage() - 1 : 0);
        Sort.Direction direction = eventFilter.getSortType() == SortTypeEnum.ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
        final PageRequest pageable = PageRequest
                .of(eventFilter.getPage(), eventFilter.getSize(), Sort.by(direction, eventFilter.getSort()));
        final Page<Event> events = findAllEventsUseCase.execute(eventFilter, pageable);
        return new EventResponseDTO(events);
    }

    @GetMapping("/{prettyName}")
    public ResponseEntity<Event> getEventByPrettyName(@PathVariable final String prettyName) {
        return ResponseEntity.ok().body(findEventByPrettyNameUseCase.execute(prettyName));
    }
}
