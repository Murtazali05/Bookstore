package ru.shop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shop.service.BookService;
import ru.shop.service.dto.BookDTO;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable Integer id){
        return bookService.getBook(id);
    }
}
