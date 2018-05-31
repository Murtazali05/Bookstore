package ru.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.entity.Book;
import ru.shop.persistense.repository.BookRepository;
import ru.shop.service.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getAllBook(){
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : books){
            bookDTOS.add(new BookDTO(book));
        }

        return bookDTOS;
    }
}
