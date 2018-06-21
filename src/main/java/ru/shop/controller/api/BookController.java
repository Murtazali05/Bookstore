package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.BookService;
import ru.shop.service.dto.book.BookDTO;
import ru.shop.service.dto.PageDTO;
import ru.shop.service.dto.PageShortDTO;
import ru.shop.service.dto.book.BookSaveDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("api/books")
@Api(tags = "Books")
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
    public BookDTO get(@PathVariable Integer id) throws NotFoundException {
        return bookService.getBook(id);
    }

    @GetMapping("/category/{code}")
    public PageDTO<BookDTO> getBooksByCategory(@PathVariable String code, @Valid PageShortDTO page){
        return bookService.getBooksByCategory(code, page);
    }

    @GetMapping("/author/{id}")
    public PageDTO<BookDTO> getBooksByAuthorId(@PathVariable Integer id, @Valid PageShortDTO page){
        return bookService.getBooksByAuthor(id, page);
    }

    @GetMapping("/search")
    public PageDTO<BookDTO> searchByBook(String query, @Valid PageShortDTO page){
        return bookService.getBooksByQuery(query, page);
    }

    @PostMapping
    public BookDTO create(@Valid @RequestBody BookSaveDTO bookDTO){
        return bookService.create(bookDTO);
    }

    @PutMapping("/{id}")
    public BookDTO update(@Valid @RequestBody BookSaveDTO bookDTO, @PathVariable("id") Integer id){
        return bookService.update(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public BookDTO delete(@PathVariable("id") Integer id){
        return bookService.delete(id);
    }

}
