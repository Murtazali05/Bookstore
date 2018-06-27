package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
