package ru.shop.controller.api;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.UserService;
import ru.shop.service.dto.user.UserCreateDTO;
import ru.shop.service.dto.user.UserDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
@Api(tags = "Users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO registration(@Valid @RequestBody UserCreateDTO userDTO){
        return userService.create(userDTO);
    }

    // confirm(email, code)

}
