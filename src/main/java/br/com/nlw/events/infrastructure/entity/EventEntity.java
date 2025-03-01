package br.com.nlw.events.infrastructure.entity;

import br.com.nlw.events.domain.enums.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "about", length = 255, nullable = false)
    private String about;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", length = 50, nullable = false)
    private EventType eventType;

    @Column(name = "pretty_name", length = 50, nullable = false, unique = true)
    private String prettyName;

    @Column(name = "location", length = 255, nullable = false)
    private String location;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    public EventEntity(String title, String about, EventType eventType, String prettyName, String location, Double price, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.title = title;
        this.about = about;
        this.eventType = eventType;
        this.prettyName = prettyName;
        this.location = location;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
