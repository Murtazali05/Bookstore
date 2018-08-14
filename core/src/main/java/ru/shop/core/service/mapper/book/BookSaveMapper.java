package ru.shop.core.service.mapper.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Author;
import ru.shop.core.persistense.entity.Book;
import ru.shop.core.persistense.entity.Category;
import ru.shop.core.persistense.entity.Genre;
import ru.shop.core.persistense.repository.AuthorRepository;
import ru.shop.core.persistense.repository.CategoryRepository;
import ru.shop.core.persistense.repository.GenreRepository;
import ru.shop.core.persistense.repository.PhotoRepository;
import ru.shop.core.service.dto.book.BookSaveDTO;
import ru.shop.core.service.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class BookSaveMapper extends AbstractMapper<Book, BookSaveDTO> {
    private PhotoRepository photoRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;
    private GenreRepository genreRepository;

    BookSaveMapper() {
        super(Book.class, BookSaveDTO.class);
    }

    @Autowired
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Autowired
    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Book toEntity(BookSaveDTO bookDTO) {
        Book book = this.toEntityWithoutId(bookDTO);
        book.setId(0);
        return book;
    }

    public Book toEntity(Integer bookId, BookSaveDTO bookDTO) {
        Book book = this.toEntityWithoutId(bookDTO);
        book.setId(bookId);
        return book;
    }

    private Book toEntityWithoutId(BookSaveDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setPrice(bookDTO.getPrice());
        book.setPubyear(bookDTO.getPubyear());
        book.setPhoto(photoRepository.getOne(bookDTO.getPhotoId()));

        Collection<Author> authors = new ArrayList<>();
        for (Integer authorId : bookDTO.getAuthorsID()) {
            authors.add(authorRepository.getOne(authorId));
        }
        book.setAuthors(authors);

        Collection<Category> categories = new ArrayList<>();
        for (Integer categoryId : bookDTO.getCategoriesID()) {
            categories.add(categoryRepository.getOne(categoryId));
        }
        book.setCategories(categories);

        Collection<Genre> genres = new ArrayList<>();
        for (Integer genreId : bookDTO.getGenresID()) {
            genres.add(genreRepository.getOne(genreId));
        }
        book.setGenres(genres);

        return book;
    }

}