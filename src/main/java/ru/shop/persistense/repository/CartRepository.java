package ru.shop.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shop.persistense.entity.Cart;
import ru.shop.persistense.entity.CartPK;

import java.math.BigDecimal;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartPK>, JpaSpecificationExecutor<Cart> {

    @Modifying
    @Query("delete from Cart c where c.pk.user.id=:userId")
    void deleteAllByUserId(@Param("userId") Integer userId);

    @Query("select SUM(c.pk.book.price * c.count) from Cart c where c.pk.user.id=:userId")
    BigDecimal getTotalPrice(@Param("userId") Integer userId);

}