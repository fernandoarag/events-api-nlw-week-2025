package br.com.nlw.events.interfaces.dtos.event;

import br.com.nlw.events.domain.model.Event;
import org.springframework.data.domain.Page;

import java.util.List;

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
        this.pageNumber = page.getNumber();
    }

    // Getters e Setters

    public List<Event> getContent() {
        return content;
    }

    public void setContent(List<Event> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
