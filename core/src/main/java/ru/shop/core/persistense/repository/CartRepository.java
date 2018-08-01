package ru.shop.core.persistense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.shop.core.persistense.entity.Cart;
import ru.shop.core.persistense.entity.CartPK;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartPK>, JpaSpecificationExecutor<Cart> {

    @Modifying
    @Query("DELETE from Cart c where c.pk.user.id=:userId")
    void deleteAllByUserId(@Param("userId") Integer userId);

    @Query("SELECT SUM(c.pk.book.price * c.count) from Cart c where c.pk.user.id=:userId")
    BigDecimal getTotalPrice(@Param("userId") Integer userId);

    @Query("SELECT DISTINCT c FROM Cart c WHERE c.pk.user.id = :id")
    List<Cart> findAllByUserId(@Param("id") Integer id);

}