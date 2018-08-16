package ru.shop.web.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder ongoing = chain.request().newBuilder();
//        session().setAttribute("user", user);
        ongoing.addHeader("Accept", "application/json;versions=1");
//        if (isUserLoggedIn()){
//            ongoing.addHeader("Authorization", SessionUtil.getSession().getAttribute("token"));
//        }
        return chain.proceed(ongoing.build());
    }
}
