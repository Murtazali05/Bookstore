package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.core.service.AuthorService;
import ru.shop.core.service.dto.PageDTO;
import ru.shop.core.service.dto.filter.PageShortDTO;
import ru.shop.core.service.dto.author.AuthorDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/authors")
@Api(tags = "Authors")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ApiOperation("Получить список всех авторов")
    public PageDTO<AuthorDTO> getAll(@Valid PageShortDTO page){
        return authorService.getAuthorsByPage(page);
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить автора по ID")
    public AuthorDTO get(@PathVariable Integer id) throws NotFoundException {
        return authorService.getAuthor(id);
    }

    @GetMapping("/book/{id}")
    @ApiOperation("Получить список авторов по ID книги")
    public List<AuthorDTO> getAuthorsByBookId(@PathVariable Integer id){
        return authorService.getAuthorsByBook(id);
    }

    @GetMapping("/search")
    @ApiOperation("Поиск авторов")
    public PageDTO<AuthorDTO> searchByAuthor(String query, @Valid PageShortDTO page){
        return authorService.getAuthorsByQuery(query, page);
    }

    @PostMapping
    @ApiOperation("Добавление автора")
    public AuthorDTO create(@Valid @RequestBody AuthorDTO authorDTO){
        return authorService.create(authorDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Редактирование автора")
    public AuthorDTO update(@Valid @RequestBody AuthorDTO authorDTO, @PathVariable("id") Integer id) throws NotFoundException {
        return authorService.update(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление автора по ID")
    public AuthorDTO delete(@PathVariable("id") Integer id) throws NotFoundException {
        return authorService.delete(id);
    }
}
