package ru.shop.service.mapper;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Book;
import ru.shop.service.dto.book.BookSaveDTO;

@Component
public class BookSaveMapper extends AbstractMapper<Book, BookSaveDTO> {

    BookSaveMapper() {
        super(Book.class, BookSaveDTO.class);
    }

    @Override
    public Book toEntity(BookSaveDTO bookDTO) {
        Book book = super.toEntity(bookDTO);
        book.setId(0);
        return book;
    }

}