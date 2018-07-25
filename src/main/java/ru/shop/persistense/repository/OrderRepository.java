package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOneById(Integer id);

}
