package ru.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.shop.service.BookService;
import ru.shop.service.dto.book.BookDTO;

import java.util.List;

@Controller
public class IndexController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String index(ModelMap map){
        List<BookDTO> bookDTOList = bookService.getBooks();

        map.addAttribute("books", bookDTOList);

        return "index";
    }
}
