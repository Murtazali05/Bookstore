package ru.shop.service.dto.author;

import ru.shop.persistense.entity.Photo;
import ru.shop.validator.annotation.ExistEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthorDTO {
    private int id;

    @NotBlank
    @Size(min = 5, max = 100)
    private String fio;

    @NotBlank
    @Size(min = 50, max = 1000)
    private String about;

    @NotNull
    @ExistEntity(entityClass = Photo.class)
    private Integer photoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

}
