package ru.shop.core.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shop.core.persistense.entity.Order;

import java.util.Collection;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOneById(Integer id);

    @Query("SELECT DISTINCT o FROM Order o WHERE o.status.code = :status")
    Collection<Order> findAllByStatus(@Param("status") String status);

}
