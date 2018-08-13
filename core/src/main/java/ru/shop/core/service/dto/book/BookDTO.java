package ru.shop.core.service.dto.book;

import ru.shop.core.service.dto.PhotoDTO;
import ru.shop.core.service.dto.author.AuthorDTO;
import ru.shop.core.service.dto.genre.GenreDTO;

import java.math.BigDecimal;
import java.util.List;

public class BookDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer pubyear;
    private BigDecimal price;
    private PhotoDTO photo;
    private List<AuthorDTO> authors;
    private List<GenreDTO> genres;

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

    public PhotoDTO getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoDTO photo) {
        this.photo = photo;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }
}
