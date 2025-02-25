package br.com.nlw.events.infrastructure.repository;

import br.com.nlw.events.domain.model.Invite;
import br.com.nlw.events.infrastructure.entity.InviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface InviteRepository extends JpaRepository<InviteEntity, UUID> {
    Invite findInviteEntityBySubscriberId(final Integer subscriberId);

    @Query(value =
            "    Select invite_hits as hits " +
            "      from tbl_invite " +
            "      join tbl_user  on(tbl_user.user_id = tbl_invite.subscribed_user_id) " +
            "      join tbl_event on(tbl_event.event_id = tbl_invite.event_id) " +
            "     where tbl_event.pretty_name = :eventPrettyName " +
            "       and tbl_user.user_id = :userId ",
            nativeQuery = true)
    Optional<Integer> getHints(@Param("eventPrettyName") String eventPrettyName, @Param("userId") Integer userId);

    @Modifying
    @Query(value =
            "    UPDATE tbl_invite " +
            "     join tbl_user on(tbl_user.user_id = tbl_invite.subscribed_user_id) " +
            "     join tbl_event on(tbl_event.event_id = tbl_invite.event_id) " +
            "      set tbl_invite.invite_hits = tbl_invite.invite_hits + 1 " +
            "    where tbl_event.pretty_name = :eventPrettyName " +
            "      and tbl_user.user_id = :userId ",
            nativeQuery = true)
    void incrementHint(@Param("eventPrettyName") String eventPrettyName, @Param("userId") Integer userId);
}
