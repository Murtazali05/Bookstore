package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.core.service.GenreService;
import ru.shop.core.service.dto.genre.GenreDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
@Api(tags = "Genres")
public class GenreController {
    private GenreService genreService;

    @Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDTO> getAll(){
        return genreService.getGenres();
    }

    @GetMapping("/{id}")
    public GenreDTO get(@PathVariable("id") Integer id) throws NotFoundException {
        return genreService.getGenre(id);
    }

    @PostMapping
    public GenreDTO create(@Valid @RequestBody GenreDTO genreDTO){
        return genreService.create(genreDTO);
    }

    @PutMapping("/{id}")
    public GenreDTO update(@Valid @RequestBody GenreDTO genreDTO, @PathVariable("id") Integer id) throws NotFoundException {
        return genreService.update(id, genreDTO);
    }

    @DeleteMapping("/{id}")
    public GenreDTO delete(@PathVariable("id") Integer id) throws NotFoundException {
        return genreService.delete(id);
    }
}
