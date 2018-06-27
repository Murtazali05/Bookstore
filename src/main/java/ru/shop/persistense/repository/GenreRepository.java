package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
