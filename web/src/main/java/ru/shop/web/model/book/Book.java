package ru.shop.web.model.book;

import ru.shop.web.model.Photo;

import java.math.BigDecimal;

public class Book {
    private Integer id;
    private String title;
    private String description;
    private Integer pubyear;
    private BigDecimal price;
    private Photo photo;

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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
