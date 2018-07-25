package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByCode(String code);

}
