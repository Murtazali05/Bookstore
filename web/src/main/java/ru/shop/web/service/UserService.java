package ru.shop.web.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.User;
import ru.shop.web.model.user.UserCreate;
import ru.shop.web.model.user.UserLogin;

public interface UserService {

    @POST("api/users/login")
    Call<TokenUser> login(@Body UserLogin userLogin);

    @POST("api/users")
    Call<User> registration(@Body UserCreate user);
}
