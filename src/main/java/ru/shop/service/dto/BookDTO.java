package ru.shop.service.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class BookDTO {
    private Integer id;
    private String title;
    private String description;
    private Date pubyear;
    private BigDecimal price;
    private PhotoDTO photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getPubyear() {
        return pubyear;
    }

    public void setPubyear(Date pubyear) {
        this.pubyear = pubyear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PhotoDTO getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDTO photo) {
        this.photo = photo;
    }

}
