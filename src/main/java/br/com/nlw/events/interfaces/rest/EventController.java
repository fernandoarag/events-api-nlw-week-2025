package br.com.nlw.events.interfaces.rest;

import br.com.nlw.events.application.usecases.event.gateway.CreateEventUseCase;
import br.com.nlw.events.application.usecases.event.gateway.FindAllEventsUseCase;
import br.com.nlw.events.application.usecases.event.gateway.FindEventByPrettyNameUseCase;
import br.com.nlw.events.domain.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Event> addNewEvent(@RequestBody final Event event) {
        return ResponseEntity.ok(createEventUseCase.execute(event));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(findAllEventsUseCase.execute());
    }

    @GetMapping("/{prettyName}")
    public ResponseEntity<Event> getEventByPrettyName(@PathVariable final String prettyName) {
        return ResponseEntity.ok().body(findEventByPrettyNameUseCase.execute(prettyName));
    }
}
