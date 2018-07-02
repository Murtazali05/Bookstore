package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
