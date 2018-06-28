package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.AuthorService;
import ru.shop.service.dto.PageDTO;
import ru.shop.service.dto.PageShortDTO;
import ru.shop.service.dto.author.AuthorDTO;

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
    public PageDTO<AuthorDTO> getAll(@Valid PageShortDTO page){
        return authorService.getAuthorsByPage(page);
    }

    @GetMapping("/{id}")
    public AuthorDTO get(@PathVariable Integer id) throws NotFoundException {
        return authorService.getAuthor(id);
    }

    @GetMapping("/book/{id}")
    public List<AuthorDTO> getAuthorsByBookId(@PathVariable Integer id){
        return authorService.getAuthorsByBook(id);
    }

    @GetMapping("/search")
    public PageDTO<AuthorDTO> searchByAuthor(String query, @Valid PageShortDTO page){
        return authorService.getAuthorsByQuery(query, page);
    }

    @PostMapping
    public AuthorDTO create(@Valid @RequestBody AuthorDTO authorDTO){
        return authorService.create(authorDTO);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(@Valid @RequestBody AuthorDTO authorDTO, @PathVariable("id") Integer id) throws NotFoundException {
        return authorService.update(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    public AuthorDTO delete(@PathVariable("id") Integer id) throws NotFoundException {
        return authorService.delete(id);
    }

}
