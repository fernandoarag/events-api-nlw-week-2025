package br.com.nlw.events.infrastructure.repositories.filter;

import br.com.nlw.events.infrastructure.entity.EventEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EventRepositoryImpl implements EventRepositoryQuery {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Page<EventEntity> filter(final EventFilter eventFilter, final Pageable pageable) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<EventEntity> criteria = builder.createQuery(EventEntity.class);
        final Root<EventEntity> root = criteria.from(EventEntity.class);

        final Predicate[] predicates = createRestrictions(eventFilter, builder, root);

        ordering(pageable, builder, root, criteria);

        criteria.select(root).where(predicates);

        // Executa a consulta com a paginação
        final TypedQuery<EventEntity> typedQuery = entityManager.createQuery(criteria);
        log.warn("resultList: {}", typedQuery.getResultList().stream().toList().size());
        final int totalElements = typedQuery.getResultList().size();
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        // Retorna os resultados paginados
        final List<EventEntity> resultList = typedQuery.getResultList();
        return new PageImpl<>(resultList, pageable, totalElements);
    }

    private static void ordering(Pageable pageable, CriteriaBuilder builder, Root<EventEntity> root, CriteriaQuery<EventEntity> criteria) {
        // Aplica a ordenação
        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order order : pageable.getSort()) {
                if (order.isAscending()) {
                    orders.add(builder.asc(root.get(order.getProperty())));
                } else {
                    orders.add(builder.desc(root.get(order.getProperty())));
                }
            }
            criteria.orderBy(orders);
        }
    }

    private static Predicate[] createRestrictions(EventFilter eventFilter, CriteriaBuilder builder, Root<EventEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotBlank(eventFilter.getTitle())) {
            predicates.add(builder.equal(root.get("title"), eventFilter.getTitle()));
        }

        if (eventFilter.getEventType() != null) {
            log.warn("EventType: {}", eventFilter.getEventType());
            List<Predicate> orPredicates = eventFilter.getEventType().stream()
                    .map(eventType -> builder.like(root.get("eventType"), "%" + eventType + "%"))
                    .toList();

            log.warn("orPredicates: {}", orPredicates);

            Predicate finalPredicate = builder.or(orPredicates.toArray(new Predicate[0]));

            predicates.add(finalPredicate);

        }

        if (StringUtils.isNotBlank(eventFilter.getPrettyName())) {
            predicates.add(builder.like(root.get("prettyName"), "%" + eventFilter.getPrettyName() + "%"));
        }

        if (StringUtils.isNotBlank(eventFilter.getLocation())) {
            predicates.add(builder.like(root.get("location"), "%" + eventFilter.getLocation() + "%"));
        }

        if (eventFilter.getStartDate() != null) {
            predicates.add(builder.like(root.get("startDate"), "%" + eventFilter.getStartDate() + "%"));
        }

        return predicates.toArray(new Predicate[0]);
    }
}