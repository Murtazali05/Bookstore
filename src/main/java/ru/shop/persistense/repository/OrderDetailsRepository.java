package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.OrderDetails;
import ru.shop.persistense.entity.OrderDetailsPK;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsPK> {

    @Modifying
    @Query("delete from OrderDetails orderDetails where orderDetails.pk.order.id=:orderId")
    void deleteAllByOrderId(@Param("orderId") Integer orderId);

}
