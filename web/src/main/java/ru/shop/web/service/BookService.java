package ru.shop.web.service;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.shop.web.model.Books;

public interface BookService {

    @GET("api/books")
    Call<Books> getAll();

}
