package br.com.nlw.events.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude = {"event", "subscriber"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_invites")
public class InviteEntity {

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private UUID id;

    @Column(name = "hits", nullable = false)
    private Long hits = 0L;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "subscribed_user_id", nullable = false)
    private UserEntity subscriber;

}
