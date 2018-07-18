package ru.shop.service.mapper;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Cart;
import ru.shop.service.dto.cart.BookInCartDTO;

@Component
public class CartMapper extends AbstractMapper<Cart, BookInCartDTO> {

    public CartMapper() {
        super(Cart.class, BookInCartDTO.class);
    }

}
