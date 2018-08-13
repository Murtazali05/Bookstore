package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shop.core.security.UserDetailsImpl;
import ru.shop.core.service.UserService;
import ru.shop.core.service.dto.TokenDTO;
import ru.shop.core.service.dto.user.UserCreateDTO;
import ru.shop.core.service.dto.user.UserDTO;
import ru.shop.core.service.dto.user.UserLoginDTO;
import ru.shop.core.service.dto.user.UserUpdateDTO;
import ru.shop.core.service.enumeration.RoleEnum;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
@Api(tags = "Users")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation("Получить список всех пользователей")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить пользователя по ID")
    public UserDTO get(@PathVariable Integer id) throws NotFoundException {
        return userService.get(id);
    }

    @PostMapping
    @ApiOperation("Регистрация пользователя")
    public UserDTO registration(@Valid @RequestBody UserCreateDTO userDTO, HttpServletRequest request){
        return userService.create(userDTO, request);
    }

    @GetMapping("/confirm")
    @ApiOperation("Подтверждение пользователя по токену")
    public TokenDTO confirmation(@RequestParam("token") String token){
        return userService.confirm(token);
    }

    @GetMapping("/login")
    @ApiOperation("Авторизация пользователя")
    public TokenDTO login(@Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PutMapping
    @ApiOperation("Редактирование пользователя")
    public UserDTO update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        return userService.update(userDetails.getId(), userUpdateDTO, request);
    }

    @PutMapping("/{id}")
    @ApiOperation("Изменить роль пользователя")
    public UserDTO changeRole(@PathVariable("id") Integer userId, RoleEnum role){
        return userService.changeRole(userId, role);
    }
}
