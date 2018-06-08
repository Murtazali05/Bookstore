package ru.shop.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PageShortDTO {
    @NotNull
    @Size(max=497)
    Integer offset;

    @NotNull
    @Size(min=1, max=500)
    Integer limit;

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

}
