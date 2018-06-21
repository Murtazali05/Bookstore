package ru.shop.service.dto.book;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class BookSaveDTO {
    @NotBlank
    @Size(min = 5, max = 200)
    private String title;

    @NotBlank
    @Size(min = 50, max = 1000)
    private String description;

    @NotNull
    @Min(0)
    @Max(3000)
    private Integer pubyear;

    @NotNull
    @Min(0)
    @Max(10000)
    private BigDecimal price;

    @NotNull
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
