package ru.shop.web.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.User;

import javax.servlet.http.HttpSession;

@Component
public class AuthSessionUtil {

    private HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public void setSession(TokenUser tokenUser) {
        getSession().setAttribute("token", tokenUser.getTokenType() + " " + tokenUser.getAccessToken());
        getSession().setAttribute("user", tokenUser.getUser());
    }

    public void deleteFromSession(){
        getSession().removeAttribute("token");
        getSession().removeAttribute("user");
    }

    public boolean isUserLogged(){
        return getSession().getAttribute("token") != null && getSession().getAttribute("user") != null;
    }

    public User getCurrentUser() {
        return (User) getSession().getAttribute("user");
    }

    public String getToken() {
        return (String) getSession().getAttribute("token");
    }
}
