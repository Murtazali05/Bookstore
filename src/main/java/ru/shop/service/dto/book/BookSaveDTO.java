package ru.shop.service.dto.book;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BookSaveDTO {
    private String title;
    private String description;

    @NotNull
    @Min(0)
    private Integer pubyear;
    private BigDecimal price;
    private Integer photoId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPubyear() {
        return pubyear;
    }

    public void setPubyear(Integer pubyear) {
        this.pubyear = pubyear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

}
