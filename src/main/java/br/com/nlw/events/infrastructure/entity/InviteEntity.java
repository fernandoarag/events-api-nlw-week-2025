package br.com.nlw.events.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "tbl_invite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InviteEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "invite_id", columnDefinition = "VARCHAR(36)")
    private UUID inviteId;

    @Column(name = "invite_hits")
    private Integer hits;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "subscribed_user_id", nullable = false)
    private UserEntity subscriber;

}
