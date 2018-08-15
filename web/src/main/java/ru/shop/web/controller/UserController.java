package ru.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.shop.web.config.JwtInterceptor;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.User;
import ru.shop.web.model.user.UserCreate;
import ru.shop.web.service.UserService;

import java.io.IOException;
import java.util.Objects;

@Controller
public class UserController {
    private Retrofit retrofit;
    private JwtInterceptor interceptor;

    @Autowired
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Autowired
    public void setInterceptor(JwtInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, ModelMap map){
        if (error != null)
            map.addAttribute("error", "Ошибка! Неверный логин или пароль!");

        return "login";
    }

    @GetMapping("/signIn")
    public ModelAndView signIn(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap map) {
        UserService userService = retrofit.create(UserService.class);
        TokenUser tokenUser;
        try {
            tokenUser = Objects.requireNonNull(userService.login(email, password).execute().body());
            interceptor.setToken(tokenUser.getAccessToken());
            map.addAttribute("user", tokenUser.getUser());
        } catch (IOException e) {
            map.addAttribute(e.getMessage());
        }

        return new ModelAndView("redirect:/user", map);
    }

    @GetMapping("/user")
    public String user(RedirectAttributes attributes, ModelMap map){
        map.addAttribute("user", attributes.getFlashAttributes());
        return "user";
    }

    @GetMapping("/registration")
    public String registration(ModelMap map) {
        map.addAttribute("user", new UserCreate());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserCreate userCreate, ModelMap map, RedirectAttributes attributes) {
        UserService userService = retrofit.create(UserService.class);

        try {
            Response<User> userResponse = userService.registration(userCreate).execute();

        } catch (IOException e) {
            map.addAttribute("error", e.getMessage());
            return "registration";
        }

        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }
}
