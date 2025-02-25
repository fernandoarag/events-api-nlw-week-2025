package br.com.nlw.events.interfaces.gateway.impl;

import br.com.nlw.events.domain.model.Event;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.mapper.EventMapper;
import br.com.nlw.events.infrastructure.repository.EventRepository;
import br.com.nlw.events.interfaces.gateway.database.EventGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<Event> findAll() {
        return eventRepository.findAll().stream().map(eventMapper::toDomain).toList();
    }

    @Override
    public Optional<Event> findByPrettyName(final String prettyName) {
        return eventRepository.findEventEntityByPrettyName(prettyName).map(eventMapper::toDomain);
    }
}
