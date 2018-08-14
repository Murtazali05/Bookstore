package ru.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Retrofit;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.User;
import ru.shop.web.model.user.UserCreate;
import ru.shop.web.service.UserService;

import java.io.IOException;
import java.util.Objects;

@Controller
public class UserController {
    private Retrofit retrofit;

    @Autowired
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, ModelMap map){
        if (error != null)
            map.addAttribute("error", "Ошибка! Неверный логин или пароль!");

        return "login";
    }

    @GetMapping("/signIn")
    public String signIn(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap map) {
        UserService userService = retrofit.create(UserService.class);
        TokenUser user;
        try {
            user = Objects.requireNonNull(userService.login(email, password).execute().body());
            map.addAttribute("user", user);

        } catch (IOException e) {
            map.addAttribute(e.getMessage());
        }

        return "redirect:/user";
    }

    @GetMapping("/user")
    public String user(@RequestBody User user, ModelMap map){
        map.addAttribute("user", user);

        return "user";
    }

    @GetMapping("/registration")
    public String registration(ModelMap map) {
        map.addAttribute("user", new UserCreate());

        return "registration";
    }
}
