package ru.shop.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.BookService;
import ru.shop.service.dto.BookDTO;
import ru.shop.service.dto.PageDTO;
import ru.shop.service.dto.PageShortDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public PageDTO<BookDTO> getAll(@Valid PageShortDTO page){
        return bookService.getBooksByPage(page);
    }

    @GetMapping("/{id}")
    public BookDTO get(@PathVariable Integer id){
        return bookService.getBook(id);
    }

    @GetMapping("/byCategory/{code}")
    public PageDTO<BookDTO> getBooksByCategory(@PathVariable String code, @Valid PageShortDTO page){
        return bookService.getBooksByCategory(code, page);
    }

    @GetMapping("/byAuthor/{id}")
    public PageDTO<BookDTO> getBooksByAuthorId(@PathVariable Integer id, @Valid PageShortDTO page){
        return bookService.getBooksByAuthor(id, page);
    }

}
