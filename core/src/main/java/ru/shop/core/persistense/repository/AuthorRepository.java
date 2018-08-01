package ru.shop.core.persistense.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shop.core.persistense.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT DISTINCT a FROM Author a INNER JOIN a.books b WHERE b.id = :id")
    List<Author> findAllByBookId(@Param("id") Integer id);

    @Query(value = "SELECT a.* FROM author as a WHERE make_tsvector(a.fio, a.about) @@ to_tsquery(?1)",
            countQuery = "SELECT count (a.*) FROM author as a WHERE make_tsvector(a.fio, a.about) @@ to_tsquery(?1)",
            nativeQuery = true)
    Page<Author> findAllByQuery(String query, Pageable pageable);

}