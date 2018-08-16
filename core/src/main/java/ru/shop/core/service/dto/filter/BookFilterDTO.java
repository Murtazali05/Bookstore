package ru.shop.core.service.dto.filter;

import ru.shop.core.validator.annotation.PubYear;

import javax.validation.constraints.Size;
import java.util.List;

public class BookFilterDTO extends PageShortDTO {
    @Size(max = 150)
    private String title;

    @Size(max = 150)
    private String author;

    @PubYear
    private Integer pubyear;

    private List<Integer> genres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPubyear() {
        return pubyear;
    }

    public void setPubyear(Integer pubyear) {
        this.pubyear = pubyear;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }
}
