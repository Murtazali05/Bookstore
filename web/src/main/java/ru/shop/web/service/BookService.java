package ru.shop.web.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.shop.web.model.Books;

public interface BookService {

    @GET("api/books")
    Call<Books> getAll(@Query("offset") Integer offset, @Query("limit") Integer limit);


}
