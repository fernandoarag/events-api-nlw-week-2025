package br.com.nlw.events.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscription {
    private Integer subscriptionNumber;
    private Event event;
    private User subscriber;
    private User indication;

    public Subscription(Integer subscriptionNumber, Event event, User subscriber, User indication) {
        this.subscriptionNumber = subscriptionNumber;
        this.event = event;
        this.subscriber = subscriber;
        this.indication = indication;
    }

    public Subscription(Event event, User subscriber, User indication) {
        this.event = event;
        this.subscriber = subscriber;
        this.indication = indication;
    }

    public Subscription() {
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "subscriptionNumber=" + subscriptionNumber +
                ", event=" + event +
                ", subscriber=" + subscriber +
                ", indication=" + indication +
                '}';
    }
}
