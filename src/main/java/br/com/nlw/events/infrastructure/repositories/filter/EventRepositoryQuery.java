package br.com.nlw.events.infrastructure.repositories.filter;

import br.com.nlw.events.infrastructure.entity.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepositoryQuery {
    Page<EventEntity> filter(EventFilter eventFilter, Pageable pageable);
}