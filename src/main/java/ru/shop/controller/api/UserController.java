package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.UserService;
import ru.shop.service.dto.TokenDTO;
import ru.shop.service.dto.user.UserCreateDTO;
import ru.shop.service.dto.user.UserDTO;
import ru.shop.service.dto.user.UserLoginDTO;

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

    @GetMapping("/login")
    public TokenDTO login(@Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PostMapping
    public UserDTO registration(@Valid @RequestBody UserCreateDTO userDTO){
        return userService.create(userDTO);
    }

    // confirm(email, code)

}
