package br.com.nlw.events.infrastructure.repositories;

import br.com.nlw.events.infrastructure.entity.InviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InviteRepository extends JpaRepository<InviteEntity, UUID> {
    Optional<InviteEntity> findInviteEntityBySubscriberIdAndEventId(Long subscriberId, Long eventId);

    InviteEntity findInviteEntityBySubscriberId(final Long subscriberId);

    @Query(value =
            "    Select hits " +
                    "      from tbl_invites " +
                    "      join tbl_users  on(tbl_users.id = tbl_invites.subscribed_user_id) " +
                    "      join tbl_events on(tbl_events.id = tbl_invites.event_id) " +
                    "     where tbl_events.pretty_name = :eventPrettyName " +
                    "       and tbl_users.id = :userId ",
            nativeQuery = true)
    Optional<Long> getHints(@Param("eventPrettyName") String eventPrettyName, @Param("userId") Long userId);

    @Modifying
    @Query(value =
            " UPDATE  tbl_invites " +
            "   join  tbl_subscriptions " +
            "     on (tbl_subscriptions.subscribed_user_id = tbl_invites.subscribed_user_id " +
            "    and tbl_subscriptions.event_id = tbl_invites.event_id) " +
            "    set tbl_invites.hits = tbl_invites.hits + 1 " +
            "  where tbl_subscriptions.subscription_number = :subscriptionNumber",
            nativeQuery = true)
    void incrementHint(@Param("subscriptionNumber") long subscriptionNumber);
}
