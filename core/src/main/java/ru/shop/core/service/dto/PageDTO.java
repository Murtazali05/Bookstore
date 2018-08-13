package ru.shop.core.service.dto;

import org.springframework.data.domain.Page;
import ru.shop.core.service.dto.filter.PageShortDTO;

import java.util.List;

public class PageDTO<T> extends PageShortDTO {
    private List<T> content;
    private Long totalElements;
    private Integer totalPages;

    public PageDTO(Page page, List<T> dtoList) {
        this.content = dtoList;
        this.setOffset(page.getNumber());
        this.setLimit(page.getNumberOfElements());
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
