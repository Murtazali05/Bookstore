package ru.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.entity.Book;
import ru.shop.service.dto.PageDTO;
import ru.shop.service.dto.PageShortDTO;
import ru.shop.service.dto.book.BookSaveDTO;
import ru.shop.service.mapper.BookMapper;
import ru.shop.persistense.repository.BookRepository;
import ru.shop.service.dto.book.BookDTO;
import ru.shop.service.mapper.BookSaveMapper;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private BookSaveMapper bookSaveMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Autowired
    public void setBookSaveMapper(BookSaveMapper bookSaveMapper) {
        this.bookSaveMapper = bookSaveMapper;
    }

    @Transactional(readOnly = true)
    public BookDTO getBook(Integer id) throws NotFoundException {
        if (!bookRepository.existsById(id))
            throw new NotFoundException("The book not found!");

        return bookMapper.toDTO(bookRepository.getOne(id));
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getBooks(){
        return bookMapper.toDTOs(bookRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PageDTO<BookDTO> getBooksByPage(PageShortDTO pageShortDTO) {
        Pageable pageable = PageRequest.of(pageShortDTO.getOffset(), pageShortDTO.getLimit(), Sort.Direction.ASC, "createdAt");
        Page<Book> booksPage = bookRepository.findAll(pageable);
        return new PageDTO<>(booksPage, bookMapper.toDTOs(booksPage.getContent()));
    }

    @Transactional(readOnly = true)
    public PageDTO<BookDTO> getBooksByCategory(String code, PageShortDTO pageShortDTO){
        Pageable pageable = PageRequest.of(pageShortDTO.getOffset(), pageShortDTO.getLimit(), Sort.Direction.ASC, "createdAt");
        Page<Book> booksPage = bookRepository.findAllByCategoryCode(code, pageable);
        return new PageDTO<>(booksPage, bookMapper.toDTOs(booksPage.getContent()));
    }

    @Transactional(readOnly = true)
    public PageDTO<BookDTO> getBooksByAuthor(Integer authorId, PageShortDTO pageShortDTO) {
        Pageable pageable = PageRequest.of(pageShortDTO.getOffset(), pageShortDTO.getLimit(), Sort.Direction.ASC, "createdAt");
        Page<Book> booksPage = bookRepository.findAllByAuthorId(authorId, pageable);
        return new PageDTO<>(booksPage, bookMapper.toDTOs(booksPage.getContent()));
    }

    @Transactional(readOnly = true)
    public PageDTO<BookDTO> getBooksByQuery(String query, PageShortDTO pageShortDTO){
        Pageable pageable = PageRequest.of(pageShortDTO.getOffset(), pageShortDTO.getLimit(), Sort.Direction.ASC, "created_at");
        Page<Book> booksPage = bookRepository.findAllByQuery(query, pageable);
        return new PageDTO<>(booksPage, bookMapper.toDTOs(booksPage.getContent()));
    }

    @Transactional
    public BookDTO create(BookSaveDTO bookDTO) {
        Book book = bookRepository.save(bookSaveMapper.toEntity(bookDTO));
        return bookMapper.toDTO(book);
    }

    @Transactional
    public BookDTO update(Integer bookId, BookSaveDTO bookDTO) {
        Book book = bookSaveMapper.toEntity(bookDTO);
        book.setId(bookId);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Transactional
    public BookDTO delete(Integer bookId){
        Book book = bookRepository.getOne(bookId);
        bookRepository.delete(book);
        return bookMapper.toDTO(book);
    }

}
