package ru.shop.web.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtInterceptor implements Interceptor {
    private AuthSessionUtil authSessionUtil;

    @Autowired
    public void setAuthSessionUtil(AuthSessionUtil authSessionUtil) {
        this.authSessionUtil = authSessionUtil;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder ongoing = chain.request().newBuilder();
        ongoing.addHeader("Accept", "application/json;versions=1");
        if (authSessionUtil.isUserLogged()){
            ongoing.addHeader("Authorization", authSessionUtil.getToken());
        }
        return chain.proceed(ongoing.build());
    }
}
