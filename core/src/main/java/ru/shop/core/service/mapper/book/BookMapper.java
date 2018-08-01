package ru.shop.core.service.mapper.book;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Book;
import ru.shop.core.service.dto.book.BookDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class BookMapper extends AbstractMapper<Book, BookDTO> {

    BookMapper() {
        super(Book.class, BookDTO.class);
    }

}
