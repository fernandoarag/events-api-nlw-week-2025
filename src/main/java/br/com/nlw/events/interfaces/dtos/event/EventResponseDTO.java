package br.com.nlw.events.interfaces.dtos.event;

import br.com.nlw.events.domain.models.Event;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
public class EventResponseDTO {
    private List<Event> content;  // Lista de eventos da página
    private int totalPages;       // Total de páginas
    private long totalElements;   // Total de elementos
    private int pageSize;         // Tamanho da página
    private int pageNumber;       // Número da página

    public EventResponseDTO(Page<Event> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber() + 1;
    }
}
