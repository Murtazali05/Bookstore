package ru.shop.service.mapper.author;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Author;
import ru.shop.service.dto.author.AuthorDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

    public AuthorMapper() {
        super(Author.class, AuthorDTO.class);
    }

}
