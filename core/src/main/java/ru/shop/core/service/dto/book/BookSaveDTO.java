package ru.shop.core.service.dto.book;

import ru.shop.core.persistense.entity.Author;
import ru.shop.core.persistense.entity.Category;
import ru.shop.core.persistense.entity.Genre;
import ru.shop.core.persistense.entity.Photo;
import ru.shop.core.validator.annotation.ExistEntity;
import ru.shop.core.validator.annotation.PubYear;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class BookSaveDTO {
    @NotBlank
    @Size(min = 5, max = 200)
    private String title;

    @NotBlank
    @Size
    private String description;

    @NotNull
    @PubYear
    private Integer pubyear;

    @NotNull
    @Min(0)
    @Max(10000)
    private BigDecimal price;

    @NotNull
    @ExistEntity(entityClass = Photo.class)
    private Integer photoId;

    @ExistEntity(entityClass = Author.class)
    private List<Integer> authorsID;

    @ExistEntity(entityClass = Category.class)
    private List<Integer> categoriesID;

    @ExistEntity(entityClass = Genre.class)
    private List<Integer> genresID;

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

    public List<Integer> getAuthorsID() {
        return authorsID;
    }

    public void setAuthorsID(List<Integer> authorsID) {
        this.authorsID = authorsID;
    }

    public List<Integer> getCategoriesID() {
        return categoriesID;
    }

    public void setCategoriesID(List<Integer> categoriesID) {
        this.categoriesID = categoriesID;
    }

    public List<Integer> getGenresID() {
        return genresID;
    }

    public void setGenresID(List<Integer> genresID) {
        this.genresID = genresID;
    }

}
