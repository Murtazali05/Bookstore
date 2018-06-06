package ru.shop.service.dto;

import ru.shop.persistense.entity.Book;
import ru.shop.persistense.entity.Photo;

import java.math.BigDecimal;
import java.sql.Date;

public class BookDTO {
    private int id;
    private String title;
    private String description;
    private Date pubyear;
    private BigDecimal price;
    private Photo photo;

    public BookDTO(Book book) {
        id = book.getId();
        title = book.getTitle();
        description = book.getDescription();
        pubyear = book.getPubyear();
        price = book.getPrice();
        photo = book.getPhoto();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
