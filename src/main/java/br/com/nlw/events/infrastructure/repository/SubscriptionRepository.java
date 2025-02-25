package br.com.nlw.events.infrastructure.repository;

import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;
import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    Optional<SubscriptionEntity> findByEventAndSubscriber(final EventEntity event, final UserEntity existingUser);

    @Query(value =
            "   Select indication_user_id as user_id, user_name, count(subscription_number) as quantidade " +
            "    from tbl_subscription " +
            "    join tbl_user " +
            "      on tbl_subscription.indication_user_id = tbl_user.user_id " +
            "   where indication_user_id is not null " +
            "     and event_id = :eventId " +
            "group by indication_user_id " +
            "order by quantidade desc ",
            nativeQuery = true)
    Optional<List<SubscriptionRankingItem>> generateRanking(@Param("eventId") Integer eventId);
}
