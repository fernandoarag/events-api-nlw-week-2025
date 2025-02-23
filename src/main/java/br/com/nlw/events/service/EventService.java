package br.com.nlw.events.service;

import br.com.nlw.events.model.Event;
import br.com.nlw.events.repo.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addNewEvent(final Event event) {
        // Gerando o pretty name
        event.setPrettyName(event.getTitle().toLowerCase().replaceAll("\\s", "-"));
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public Event getByPrettyName(final String prettyName) {
        return eventRepository.findByPrettyName(prettyName);
    }
}
