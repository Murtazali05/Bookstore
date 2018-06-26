package ru.shop.service.mapper.book;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Book;
import ru.shop.service.dto.book.BookDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class BookMapper extends AbstractMapper<Book, BookDTO> {

    BookMapper() {
        super(Book.class, BookDTO.class);
    }

}
