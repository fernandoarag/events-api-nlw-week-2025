package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.mapper.EventMapper;
import br.com.nlw.events.infrastructure.repositories.EventRepository;
import br.com.nlw.events.infrastructure.repositories.filter.EventFilter;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventJpaGateway implements EventGateway {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public Event save(final Event event) {
        final EventEntity eventEntity = eventMapper.toEntity(event);
        return eventMapper.toDomain(eventRepository.save(eventEntity));
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(eventMapper::toDomain);
    }

    @Override
    public Optional<Event> findByPrettyName(final String prettyName) {
        return eventRepository.findEventEntityByPrettyName(prettyName)
                .map(eventMapper::toDomain);
    }

    @Override
    public Page<Event> filter(EventFilter eventFilter, Pageable pageable) {
        return eventRepository.filter(eventFilter, pageable)
                .map(eventMapper::toDomain);
    }
}
