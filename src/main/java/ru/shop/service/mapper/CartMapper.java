package ru.shop.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Cart;
import ru.shop.service.dto.cart.BookInCartDTO;
import ru.shop.service.mapper.book.BookMapper;

@Component
public class CartMapper extends AbstractMapper<Cart, BookInCartDTO> {
    private BookMapper bookMapper;

    public CartMapper() {
        super(Cart.class, BookInCartDTO.class);
    }

    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public BookInCartDTO toDTO(Cart cart) {
        BookInCartDTO bookInCartDTO = new BookInCartDTO();
        bookInCartDTO.setCount(cart.getCount());
        bookInCartDTO.setBook(bookMapper.toDTO(cart.getPk().getBook()));
        return bookInCartDTO;
    }
}
