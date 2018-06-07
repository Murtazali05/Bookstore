package ru.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.service.mapper.BookMapper;
import ru.shop.persistense.repository.BookRepository;
import ru.shop.service.dto.BookDTO;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Transactional(readOnly = true)
    public BookDTO getBook(Integer id){
        return bookMapper.toDTO(bookRepository.getOne(id));
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getBooks(){
        return bookMapper.toDTOs(bookRepository.findAll());
    }
}
