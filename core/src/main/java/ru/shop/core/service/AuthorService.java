package ru.shop.core.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.core.persistense.entity.Author;
import ru.shop.core.persistense.repository.AuthorRepository;
import ru.shop.core.service.dto.PageDTO;
import ru.shop.core.service.dto.PageShortDTO;
import ru.shop.core.service.dto.author.AuthorDTO;
import ru.shop.core.service.mapper.author.AuthorMapper;

import java.util.List;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setAuthorMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getAuthors(){
        return authorMapper.toDTOs(authorRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PageDTO<AuthorDTO> getAuthorsByPage(PageShortDTO pageShortDTO) {
        Pageable pageable = PageRequest.of(pageShortDTO.getOffset(), pageShortDTO.getLimit(), Sort.Direction.ASC, "fio");
        Page<Author> authorsPage = authorRepository.findAll(pageable);
        return new PageDTO<>(authorsPage, authorMapper.toDTOs(authorsPage.getContent()));
    }

    @Transactional(readOnly = true)
    public AuthorDTO getAuthor(Integer id) throws NotFoundException {
        if (!authorRepository.existsById(id))
            throw new NotFoundException("Author with such id=" + id + " does not found!");

        return authorMapper.toDTO(authorRepository.getOne(id));
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getAuthorsByBook(Integer bookId) {
        return authorMapper.toDTOs(authorRepository.findAllByBookId(bookId));
    }

    @Transactional(readOnly = true)
    public PageDTO<AuthorDTO> getAuthorsByQuery(String query, PageShortDTO pageShortDTO) {
        Pageable pageable = PageRequest.of(pageShortDTO.getOffset(), pageShortDTO.getLimit(), Sort.Direction.ASC, "fio");
        Page<Author> authorsPage = authorRepository.findAllByQuery(query, pageable);
        return new PageDTO<>(authorsPage, authorMapper.toDTOs(authorsPage.getContent()));
    }

    @Transactional
    public AuthorDTO create(AuthorDTO authorDTO) {
        Author author = authorRepository.save(authorMapper.toEntity(authorDTO));
        return authorMapper.toDTO(author);
    }

    @Transactional
    public AuthorDTO update(Integer authorId, AuthorDTO authorDTO) throws NotFoundException {
        if (!authorRepository.existsById(authorId))
            throw new NotFoundException("Author with such id=" + authorId + " does not exist!");

        authorDTO.setId(authorId);
        Author author = authorMapper.toEntity(authorDTO);
        return authorMapper.toDTO(authorRepository.save(author));
    }

    @Transactional
    public AuthorDTO delete(Integer id) throws NotFoundException {
        if (!authorRepository.existsById(id))
            throw new NotFoundException("Author with such id=" + id + " does not exist!");

        Author author = authorRepository.getOne(id);
        authorRepository.delete(author);
        return authorMapper.toDTO(author);
    }

}
