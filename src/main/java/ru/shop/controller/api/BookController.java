package ru.shop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.BookService;
import ru.shop.service.dto.BookDTO;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAll(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public BookDTO get(@PathVariable Integer id){
        return bookService.getBook(id);
    }

}
