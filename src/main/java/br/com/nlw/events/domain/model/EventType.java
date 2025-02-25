package br.com.nlw.events.domain.model;

public enum EventType {
    ONLINE_AO_VIVO("AO VIVO"),
    ONLINE_GRAVADO("GRAVADO"),
    PRESENCIAL("PRESENCIAL");

    private String description;

    EventType(String description) {
        this.description = description;
    }
}
