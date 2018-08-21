package ru.shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.shop.web.config.AuthSessionUtil;
import ru.shop.web.config.messages.MessageContainer;
import ru.shop.web.model.book.Books;
import ru.shop.web.model.error.ErrorMap;
import ru.shop.web.service.BookService;
import ru.shop.web.util.ErrorUtil;

import java.io.IOException;
import java.util.Map;

@Controller
public class BookController {
    private Retrofit retrofit;
    private AuthSessionUtil authSession;
    private MessageContainer messages;
    private ErrorUtil errorUtil;

    @Autowired
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Autowired
    public void setAuthSession(AuthSessionUtil authSession) {
        this.authSession = authSession;
    }

    @Autowired
    public void setMessages(MessageContainer messages) {
        this.messages = messages;
    }

    @Autowired
    public void setErrorUtil(ErrorUtil errorUtil) {
        this.errorUtil = errorUtil;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                              @RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit,
                              ModelMap map) throws IOException {
        BookService bookService = retrofit.create(BookService.class);

        Response<?> response = bookService.getAll(offset, limit).execute();

        if (response.isSuccessful()) {
            Books books = (Books) response.body();

            assert books != null;
            map.addAttribute("books", books);
        } else {
            switch (response.code()) {
                case 400:
                    map.addAttribute("error", getErrorMsg(errorUtil.parseErrorMap(response)));
                    break;
                default:
                    map.addAttribute("error", errorUtil.parseError(response).getMessage());
                    break;
            }
        }

        if (authSession.isUserLogged()){
            map.addAttribute("user", authSession.getCurrentUser());
        }

        return new ModelAndView("index");
    }

    private String getErrorMsg(ErrorMap errorMap) {
        Map<String, String> errors = errorMap.getErrors();
        String error = errorMap.getMessage();

        for (String key : errors.keySet()){
            String msg = errors.get(key).replace("{", "").replace("}", "");
            error = "Error: " + messages.getAndFormat(msg, key) + ", field: " + key;
        }
        return error;
    }
}
