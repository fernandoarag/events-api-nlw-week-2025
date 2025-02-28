package br.com.nlw.events.domain.model;

import lombok.Getter;

@Getter
public enum EventType {
    ONLINE_AO_VIVO("AO VIVO"),
    ONLINE_GRAVADO("GRAVADO"),
    PRESENCIAL("PRESENCIAL");

    private final String description;

    EventType(String description) {
        this.description = description;
    }

}