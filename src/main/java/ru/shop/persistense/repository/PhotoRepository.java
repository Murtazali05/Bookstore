package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

}
