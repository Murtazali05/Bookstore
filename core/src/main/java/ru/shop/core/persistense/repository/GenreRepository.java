package ru.shop.core.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.core.persistense.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
