package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<UserDTO> getAll() throws NotFoundException {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable Integer id) throws NotFoundException {
        return userService.get(id);
    }

    @PostMapping
    public UserDTO registration(@Valid @RequestBody UserCreateDTO userDTO, HttpServletRequest request){
        return userService.create(userDTO, request);
    }

    @GetMapping("/confirm")
    public TokenDTO confirmation(@RequestParam("token") String token){
        return userService.confirm(token);
    }

    @GetMapping("/login")
    public TokenDTO login(@Valid UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @PutMapping
    public UserDTO update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        return userService.update(userDetails.getId(), userUpdateDTO, request);
    }

    @PutMapping("/{id}")
    public UserDTO changeRole(@PathVariable("id") Integer userId, RoleEnum role){
        return userService.changeRole(userId, role);
    }
}
