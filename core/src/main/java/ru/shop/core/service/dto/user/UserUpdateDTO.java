package ru.shop.core.service.dto.user;

import ru.shop.core.persistense.entity.Photo;
import ru.shop.core.validator.annotation.Birthday;
import ru.shop.core.validator.annotation.ExistEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

public class UserUpdateDTO {
    @NotBlank
    @Size(min = 3, max = 45)
    private String surname;

    @NotBlank
    @Size(min = 3, max = 45)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 30)
    private String oldPassword;

    @NotBlank
    @Size(min = 3, max = 30)
    private String newPassword;

    @Birthday
    private Date birthday;

    @ExistEntity(entityClass = Photo.class)
    private Integer photoId;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }
}
