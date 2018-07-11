package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.UserService;
import ru.shop.service.dto.TokenDTO;
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

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable Integer id) throws NotFoundException {
        return userService.getUser(id);
    }

    @PostMapping
    public UserDTO registration(@Valid @RequestBody UserCreateDTO userDTO){
        return userService.create(userDTO);
    }

    @PostMapping("/login")
    public TokenDTO login(String email, String password) throws NotFoundException {
        return userService.login(email, password);
    }
    // confirm(email, code)

}
