package ru.shop.web.model.book;

import java.util.List;

public class Books {
    private Integer offset;
    private Integer limit;
    private List<Book> content;
    private Integer totalElements;
    private Integer totalPages;

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

    public List<Book> getContent() {
        return content;
    }

    public void setContent(List<Book> content) {
        this.content = content;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
