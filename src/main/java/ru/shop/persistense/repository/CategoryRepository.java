package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
