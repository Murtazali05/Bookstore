package ru.shop.core.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PageShortDTO {
    @Max(497)
    private Integer offset = 0;

    @Min(1)
    @Max(500)
    private Integer limit = 30;

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
