package ru.shop.service.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDTO<T> extends PageShortDTO{
    private List<T> content;
    private Long totalElements;
    private Integer totalPages;

    public PageDTO(Page page, List<T> dtoList) {
        this.content = dtoList;
        this.offset = page.getNumber();
        this.limit = page.getNumberOfElements();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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
