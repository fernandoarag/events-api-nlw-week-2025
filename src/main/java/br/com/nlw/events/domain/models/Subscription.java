package br.com.nlw.events.domain.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    private Long subscriptionNumber;
    private Event event;
    private User subscriber;
    private User indication;

    public Subscription(Event event, User subscriber, User indication) {
        this.event = event;
        this.subscriber = subscriber;
        this.indication = indication;
    }
}
