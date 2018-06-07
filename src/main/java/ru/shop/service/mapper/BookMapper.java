package ru.shop.service.mapper;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Book;
import ru.shop.service.dto.BookDTO;

@Component
public class BookMapper extends AbstractMapper<Book, BookDTO> {

    BookMapper() {
        super(Book.class, BookDTO.class);
    }

}
