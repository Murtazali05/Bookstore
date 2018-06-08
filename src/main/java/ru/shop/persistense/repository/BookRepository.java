package ru.shop.persistense.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT DISTINCT b FROM Book b INNER JOIN b.categories c WHERE c.code = :code")
    Page<Book> findAllByCategoryCode(@Param("code") String code, Pageable pageable);

    @Query("SELECT DISTINCT b FROM Book b INNER JOIN b.authors a WHERE a.id = :id")
    Page<Book> findAllByAuthorId(@Param("id") Integer id, Pageable pageable);

}
