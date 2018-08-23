package ru.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.shop.web.config.AuthSessionUtil;
import ru.shop.web.config.messages.MessageContainer;
import ru.shop.web.model.error.ErrorMap;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.UserCreate;
import ru.shop.web.model.user.UserLogin;
import ru.shop.web.service.UserService;
import ru.shop.web.util.ErrorUtil;

import java.io.IOException;
import java.util.Map;

@Controller
public class UserController {
    private Retrofit retrofit;
    private AuthSessionUtil authSession;
    private MessageContainer messages;
    private ErrorUtil errorUtil;

    @Autowired
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Autowired
    public void setAuthSession(AuthSessionUtil authSession) {
        this.authSession = authSession;
    }

    @Autowired
    public void setMessages(MessageContainer messages) {
        this.messages = messages;
    }

    @Autowired
    public void setErrorUtil(ErrorUtil errorUtil) {
        this.errorUtil = errorUtil;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, ModelMap map) {
        if (authSession.isUserLogged())
            return new ModelAndView("redirect:/user");

        if (error != null)
            map.addAttribute("error", messages.get(error));

        return new ModelAndView("login", map);
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserLogin userLogin, ModelMap map) throws IOException {
        UserService userService = retrofit.create(UserService.class);
        String view;

        Response<?> response = userService.login(userLogin).execute();

        if (response.isSuccessful()) {
            TokenUser tokenUser = (TokenUser) response.body();
            authSession.setSession(tokenUser);
            view = "redirect:/user";
        } else {
            view = "login";
            switch (response.code()) {
                case 400:
                    map.addAttribute("error", getErrorMsg(errorUtil.parseErrorMap(response)));
                    break;
                case 401:
                    map.addAttribute("error", errorUtil.parseError(response).getMessage());
                    break;
                default:
                    map.addAttribute("error", errorUtil.parseError(response).getMessage());
                    break;
            }
        }

        return new ModelAndView(view, map);
    }

    @GetMapping("/user")
    public ModelAndView user(ModelMap map) {
        if (authSession.isUserLogged()) {
            map.addAttribute("user", authSession.getCurrentUser());
            return new ModelAndView("user", map);
        } else {
            return new ModelAndView("redirect:/login?error=forbidden.page.unauthorized.message");
        }
    }

    @GetMapping("/registration")
    public ModelAndView registration(ModelMap map) {
        if (authSession.isUserLogged())
            return new ModelAndView("redirect:/user");

        map.addAttribute("userCreate", new UserCreate());

        return new ModelAndView("registration", map);
    }

    @PostMapping("/registration")
    public ModelAndView registration(@ModelAttribute UserCreate userCreate, ModelMap map) throws IOException {
        UserService userService = retrofit.create(UserService.class);
        String view;

        Response<?> response = userService.registration(userCreate).execute();

        if (response.isSuccessful()) {
            view = "redirect:/confirmation";
        } else {
            map.addAttribute("userCreate", userCreate);
            view = "registration";
            switch (response.code()) {
                case 400:
                    ErrorMap errorMap = errorUtil.parseErrorMap(response);
                    map.addAttribute("error", getErrorMsg(errorMap));
                    break;
                case 401:
                    map.addAttribute("error", errorUtil.parseError(response).getMessage());
                    break;
                default:
                    map.addAttribute("error", errorUtil.parseError(response).getMessage());
                    break;
            }
        }

        return new ModelAndView(view, map);
    }

    @GetMapping("/confirmation")
    public ModelAndView confirmation() {
        return new ModelAndView("confirmation");
    }

    @GetMapping("/logout")
    public ModelAndView logout(){
        authSession.deleteFromSession();
        return new ModelAndView("redirect:/");
    }

    private String getErrorMsg(ErrorMap errorMap) {
        Map<String, String> errors = errorMap.getErrors();
        String error = errorMap.getMessage();

        for (String key : errors.keySet()){
            String msg = errors.get(key).replace("{", "").replace("}", "");
            error = "Error: " + messages.getAndFormat(msg, key) + ", field: " + key;
        }
        return error;
    }
}
