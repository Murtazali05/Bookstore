package ru.shop.web.controller;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.shop.web.config.AuthSessionUtil;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.service.VkAPIService;

@Controller
@RequestMapping("/vk")
public class VkAPIController {
    private VkAPIService vkAPI;
    private AuthSessionUtil authSession;

    @Autowired
    public void setVkAPI(VkAPIService vkAPI) {
        this.vkAPI = vkAPI;
    }

    @Autowired
    public void setAuthSession(AuthSessionUtil authSession) {
        this.authSession = authSession;
    }

    @GetMapping("/login")
    public ModelAndView loginVk(@RequestParam("code") String code, ModelMap map) {

        try {
            TokenUser tokenUser = vkAPI.loginVk(code);
            authSession.setSession(tokenUser);
        } catch (ClientException | ApiException e) {
            map.addAttribute("error", e.getMessage());
            return new ModelAndView("login", map);
        }

        return new ModelAndView("redirect:/user");
    }
}
