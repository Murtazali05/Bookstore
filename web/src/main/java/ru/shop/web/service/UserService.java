package ru.shop.web.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.User;
import ru.shop.web.model.user.UserCreate;

public interface UserService {

    @GET("api/users/login")
    Call<TokenUser> login(@Query("email") String email, @Query("password") String password);

    @POST("api/users")
    Call<User> registration(@Body UserCreate user);
}
