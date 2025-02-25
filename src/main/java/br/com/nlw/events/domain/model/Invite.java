package br.com.nlw.events.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invite {
    private UUID inviteId;
    private Integer hits;
    private Event event;
    private User subscriber;

    public Invite(Integer hits, Event event, User subscriber) {
        this.hits = hits;
        this.event = event;
        this.subscriber = subscriber;
    }
}
