package br.com.nlw.events.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tbl_subscription")
@Getter
@Setter
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_number")
    private Integer subscriptionNumber;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "subscribed_user_id")
    private UserEntity subscriber;

    @ManyToOne
    @JoinColumn(name = "indication_user_id")
    private UserEntity indication;

    public SubscriptionEntity(EventEntity event, UserEntity subscriber, UserEntity indication) {
        this.event = event;
        this.subscriber = subscriber;
        this.indication = indication;
    }

    public SubscriptionEntity() {
    }
}
