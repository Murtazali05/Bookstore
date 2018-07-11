package ru.shop.service.dto;

import ru.shop.service.dto.user.UserDTO;

public class TokenDTO {
    private String token;
    private UserDTO userDTO;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
