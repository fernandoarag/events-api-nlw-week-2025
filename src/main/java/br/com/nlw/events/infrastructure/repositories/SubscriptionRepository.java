package br.com.nlw.events.infrastructure.repositories;

import br.com.nlw.events.infrastructure.entity.EventEntity;
import br.com.nlw.events.infrastructure.entity.SubscriptionEntity;
import br.com.nlw.events.infrastructure.entity.UserEntity;
import br.com.nlw.events.interfaces.dtos.subscription.SubscriptionRankingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    Optional<SubscriptionEntity> findByEventAndSubscriber(final EventEntity event, final UserEntity existingUser);

    @Query(value =
            "   Select indication_user_id as user_id, username, count(subscription_number) as quantidade " +
                    "    from tbl_subscriptions " +
                    "    join tbl_users " +
                    "      on tbl_subscriptions.indication_user_id = tbl_users.id " +
                    "   where indication_user_id is not null " +
                    "     and event_id = :eventId " +
                    "group by indication_user_id " +
                    "order by quantidade desc ",
            nativeQuery = true)
    Optional<List<SubscriptionRankingItem>> generateRanking(@Param("eventId") Long eventId);
}
