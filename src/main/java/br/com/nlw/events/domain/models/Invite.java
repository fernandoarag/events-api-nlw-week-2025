package br.com.nlw.events.domain.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Invite {

    private UUID id;
    private Long hits;
    private Event event;
    private User subscriber;

    public Invite(Long hits, Event event, User subscriber) {
        this.hits = hits;
        this.event = event;
        this.subscriber = subscriber;
    }
}
