package br.com.nlw.events.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"event", "subscriber", "indication"})
@Table(name = "tbl_subscriptions")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_number")
    private Long subscriptionNumber;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "subscribed_user_id", nullable = false)
    private UserEntity subscriber;

    @ManyToOne
    @JoinColumn(name = "indication_user_id")
    private UserEntity indication;

    public SubscriptionEntity(EventEntity event, UserEntity subscriber, UserEntity indication) {
        this.event = event;
        this.subscriber = subscriber;
        this.indication = indication;
    }

}
