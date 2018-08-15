package ru.shop.web.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtInterceptor implements Interceptor {
    private String token;

    public void setToken(String token) {
        this.token = "Bearer " + token;
    }

    private String getToken(){
        return token;
    }

    private Boolean isUserLoggedIn() {
        return token != null;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder ongoing = chain.request().newBuilder();
        ongoing.addHeader("Accept", "application/json;versions=1");
        if (isUserLoggedIn()){
            ongoing.addHeader("Authorization", getToken());
        }
        return chain.proceed(ongoing.build());
    }

    public void deleteToken(){
        token = null;
    }
}
