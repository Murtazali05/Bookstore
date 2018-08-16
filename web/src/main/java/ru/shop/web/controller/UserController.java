package ru.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.User;
import ru.shop.web.model.user.UserCreate;
import ru.shop.web.model.user.UserLogin;
import ru.shop.web.service.UserService;

import java.io.IOException;

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
            map.addAttribute("error", error);

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserLogin userLogin, ModelMap map) {
        UserService userService = retrofit.create(UserService.class);
        String page;

        try {
            Response<?> response = userService.login(userLogin).execute();
            switch (response.code()) {
                case 200:
                    TokenUser tokenUser = (TokenUser) response.body();
                    // Добавить tokenUser в sessionUtil
                    page = "redirect:/user";
                    break;
                case 400:
                    page = "redirect:/login?error=" + response.errorBody();
                    break;
                case 401:
                    page = "redirect:/login?error=" + response.errorBody();
                    break;
                default:
                    page = "redirect:/500";
                    break;
            }
        } catch (IOException e) {
            return "/login?error=" + e.getMessage();
        }

        return page;
    }

    @GetMapping("/user")
    public String user(ModelMap map){

        map.addAttribute("user", new User());   // User from SessionUtil
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
        String page;

        try {
            Response<?> response = userService.registration(userCreate).execute();
            switch (response.code()) {
                case 200:
                    page = "redirect:/confirmation";
                    break;
                case 400:
                    page = "redirect:/registration?error=" + response.errorBody();
                    break;
                case 401:
                    page = "redirect:/registration?error=" + response.errorBody();
                    break;
                default:
                    page = "redirect:/500";
                    break;
            }
        } catch (IOException e) {
            map.addAttribute("error", e.getMessage());
            return "registration";
        }

        return page;
    }

    @GetMapping("/confirmation")
    public String confirmation() {
        return "confirmation";
    }
}
