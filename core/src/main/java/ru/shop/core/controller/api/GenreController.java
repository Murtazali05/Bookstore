package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("Получить список всех жанров")
    public List<GenreDTO> getAll(){
        return genreService.getGenres();
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить жанр по ID")
    public GenreDTO get(@PathVariable("id") Integer id) throws NotFoundException {
        return genreService.getGenre(id);
    }

    @PostMapping
    @ApiOperation("Создать новый жанр")
    public GenreDTO create(@Valid @RequestBody GenreDTO genreDTO){
        return genreService.create(genreDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Редактировать жанр")
    public GenreDTO update(@Valid @RequestBody GenreDTO genreDTO, @PathVariable("id") Integer id) throws NotFoundException {
        return genreService.update(id, genreDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить жанр по ID")
    public GenreDTO delete(@PathVariable("id") Integer id) throws NotFoundException {
        return genreService.delete(id);
    }
}
