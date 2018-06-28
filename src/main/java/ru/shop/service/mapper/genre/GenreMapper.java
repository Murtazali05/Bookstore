package ru.shop.service.mapper.genre;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Genre;
import ru.shop.service.dto.genre.GenreDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class GenreMapper extends AbstractMapper<Genre, GenreDTO> {

    public GenreMapper() {
        super(Genre.class, GenreDTO.class);
    }

}