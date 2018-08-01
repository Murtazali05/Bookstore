package ru.shop.core.service.mapper.genre;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Genre;
import ru.shop.core.service.dto.genre.GenreDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class GenreMapper extends AbstractMapper<Genre, GenreDTO> {

    public GenreMapper() {
        super(Genre.class, GenreDTO.class);
    }

}