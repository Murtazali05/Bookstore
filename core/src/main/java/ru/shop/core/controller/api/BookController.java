package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.core.service.BookService;
import ru.shop.core.service.dto.PageDTO;
import ru.shop.core.service.dto.PageShortDTO;
import ru.shop.core.service.dto.book.BookDTO;
import ru.shop.core.service.dto.book.BookSaveDTO;

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
    @ApiOperation("Получить все книги")
    public PageDTO<BookDTO> getAll(@Valid PageShortDTO page){
        return bookService.getBooksByPage(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить книгу по ID")
    public BookDTO get(@PathVariable Integer id) throws NotFoundException {
        return bookService.getBook(id);
    }

    @GetMapping("/category/{code}")
    @ApiOperation("Получить список книг по категории")
    public PageDTO<BookDTO> getBooksByCategory(@PathVariable String code, @Valid PageShortDTO page){
        return bookService.getBooksByCategory(code, page);
    }

    @GetMapping("/author/{id}")
    @ApiOperation("Получить книги по автору(по ID автора)")
    public PageDTO<BookDTO> getBooksByAuthorId(@PathVariable Integer id, @Valid PageShortDTO page){
        return bookService.getBooksByAuthor(id, page);
    }

    @GetMapping("/search")
    @ApiOperation("Поиск книг")
    public PageDTO<BookDTO> searchByBook(String query, @Valid PageShortDTO page){
        return bookService.getBooksByQuery(query, page);
    }

    @PostMapping
    @ApiOperation("Добавление книги")
    public BookDTO create(@Valid @RequestBody BookSaveDTO bookDTO){
        return bookService.create(bookDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Редактирование книги")
    public BookDTO update(@Valid @RequestBody BookSaveDTO bookDTO, @PathVariable("id") Integer id) throws NotFoundException {
        return bookService.update(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление книги по ID")
    public BookDTO delete(@PathVariable("id") Integer id) throws NotFoundException {
        return bookService.delete(id);
    }
}
