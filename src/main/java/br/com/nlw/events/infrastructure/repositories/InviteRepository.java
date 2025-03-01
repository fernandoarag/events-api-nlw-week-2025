package br.com.nlw.events.infrastructure.repositories;

import br.com.nlw.events.domain.models.Invite;
import br.com.nlw.events.infrastructure.entity.InviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface InviteRepository extends JpaRepository<InviteEntity, UUID> {
    Invite findInviteEntityBySubscriberId(final Long subscriberId);

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
            "    UPDATE tbl_invites " +
            "     join tbl_users on(tbl_users.id = tbl_invites.subscribed_user_id) " +
            "     join tbl_events on(tbl_events.id = tbl_invites.event_id) " +
            "      set tbl_invites.hits = tbl_invites.hits + 1 " +
            "    where tbl_events.pretty_name = :eventPrettyName " +
            "      and tbl_users.id = :userId ",
            nativeQuery = true)
    void incrementHint(@Param("eventPrettyName") String eventPrettyName, @Param("userId") Long userId);
}
