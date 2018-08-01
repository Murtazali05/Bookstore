package ru.shop.core.service.dto.genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GenreDTO {
    private Integer id;

    @NotBlank
    @Size(min=3, max=45)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
