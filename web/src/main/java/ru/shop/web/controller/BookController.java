package ru.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import retrofit2.Retrofit;
import ru.shop.web.model.Book;
import ru.shop.web.service.BookService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {
    private Retrofit retrofit;

    @Autowired
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @GetMapping("/")
    public String index(ModelMap map) throws IOException {
        BookService bookService = retrofit.create(BookService.class);
        List<Book> books = Objects.requireNonNull(bookService.getAll(0, 10).execute().body()).getContent();

        map.addAttribute("books", books);

        return "index";
    }
}
