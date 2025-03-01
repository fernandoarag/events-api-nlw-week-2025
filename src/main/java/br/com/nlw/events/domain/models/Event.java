package br.com.nlw.events.domain.models;

import br.com.nlw.events.domain.enums.EventType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private Long id;
    private String title;
    private String about;
    private EventType eventType;
    private String prettyName;
    private String location;
    private Double price;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

}

