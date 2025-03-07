package br.com.nlw.events.infrastructure.repositories.filter;

import br.com.nlw.events.domain.enums.EventType;
import br.com.nlw.events.interfaces.dtos.event.SortTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventFilter {

    private String title;
    private List<EventType> eventType;
    private String prettyName;
    private String location;
    private LocalDate startDate;
    private int page;
    private int size = 4;
    private String sort = "startDate";
    private SortTypeEnum sortType = SortTypeEnum.ASC;

}