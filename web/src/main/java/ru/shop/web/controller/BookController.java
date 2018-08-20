package ru.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Retrofit;
import ru.shop.web.config.AuthSessionUtil;
import ru.shop.web.model.book.Book;
import ru.shop.web.service.BookService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {
    private Retrofit retrofit;
    private AuthSessionUtil authSession;

    @Autowired
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Autowired
    public void setAuthSession(AuthSessionUtil authSession) {
        this.authSession = authSession;
    }

    @GetMapping("/")
    public ModelAndView index(ModelMap map) throws IOException {
        BookService bookService = retrofit.create(BookService.class);
        List<Book> books = Objects.requireNonNull(bookService.getAll().execute().body()).getContent();

        map.addAttribute("books", books);

        if (authSession.isUserLogged()){
            map.addAttribute("user", authSession.getCurrentUser());
        }

        return new ModelAndView("index");
    }
}
