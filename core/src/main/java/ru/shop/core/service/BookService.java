package ru.shop.core.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.core.persistense.entity.Book;
import ru.shop.core.persistense.repository.BookRepository;
import ru.shop.core.service.dto.PageDTO;
import ru.shop.core.service.dto.book.BookDTO;
import ru.shop.core.service.dto.book.BookSaveDTO;
import ru.shop.core.service.dto.filter.BookFilterDTO;
import ru.shop.core.persistense.specification.BookSpecification;
import ru.shop.core.service.dto.filter.PageShortDTO;
import ru.shop.core.service.mapper.book.BookMapper;
import ru.shop.core.service.mapper.book.BookSaveMapper;

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
    public PageDTO<BookDTO> getBooksByFilter(BookFilterDTO bookFilterDTO) {
        Pageable pageable = PageRequest.of(bookFilterDTO.getOffset(), bookFilterDTO.getLimit(), Sort.Direction.ASC, "createdAt");
        Page<Book> booksPage = bookRepository.findAll(new BookSpecification(bookFilterDTO), pageable);
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
    public BookDTO update(Integer bookId, BookSaveDTO bookDTO) throws NotFoundException {
        if (!bookRepository.existsById(bookId))
            throw new NotFoundException("Book with such id=" + bookId + " does not exist!");

        Book book = bookSaveMapper.toEntity(bookId, bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Transactional
    public BookDTO delete(Integer bookId) throws NotFoundException {
        if (!bookRepository.existsById(bookId))
            throw new NotFoundException("Book with such id=" + bookId + " does not exist!");

        Book book = bookRepository.getOne(bookId);
        bookRepository.delete(book);
        return bookMapper.toDTO(book);
    }

}
