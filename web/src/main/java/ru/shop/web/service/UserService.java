package ru.shop.web.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.shop.web.model.TokenUser;

public interface UserService {

    @GET("api/users/login")
    Call<TokenUser> login(@Query("email") String email, @Query("password") String password);

}
