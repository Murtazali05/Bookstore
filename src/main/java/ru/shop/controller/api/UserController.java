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
import ru.shop.service.dto.user.UserUpdateDTO;

import javax.servlet.http.HttpServletRequest;
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
    public UserDTO registration(@Valid @RequestBody UserCreateDTO userDTO, HttpServletRequest request){
        return userService.create(userDTO, request.getRequestURL().toString());
    }

    @GetMapping("/confirm")
    public TokenDTO confirmation(@RequestParam("token") String token){
        return userService.confirm(token);
    }

    @GetMapping("/login")
    public TokenDTO login(@Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PutMapping("/{id}")
    public UserDTO update(@Valid @RequestBody UserUpdateDTO userDTO, @PathVariable("id") Integer id) {
        return userService.update(id, userDTO);
    }

}
