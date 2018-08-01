package ru.shop.core.service.dto.user;

import ru.shop.core.persistense.entity.User;
import ru.shop.core.validator.annotation.ExistEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    @NotBlank
    @Email
    @ExistEntity(entityClass = User.class)
    private String email;

    @NotBlank
    @Size(min = 3, max = 30)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
