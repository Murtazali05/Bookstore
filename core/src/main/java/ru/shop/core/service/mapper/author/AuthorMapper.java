package ru.shop.core.service.mapper.author;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Author;
import ru.shop.core.service.dto.author.AuthorDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

    public AuthorMapper() {
        super(Author.class, AuthorDTO.class);
    }

}
