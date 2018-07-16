package ru.shop.service.dto.user;

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
    private String password;

    private Date birthday;
    private Boolean confirmation;
    private String confirmCode;
    private Integer roleId;
    private Integer photoId;
}
