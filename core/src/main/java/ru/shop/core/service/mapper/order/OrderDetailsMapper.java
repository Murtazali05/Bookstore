package ru.shop.core.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.OrderDetails;
import ru.shop.core.service.dto.order.BookInOrderDTO;
import ru.shop.core.service.mapper.AbstractMapper;
import ru.shop.core.service.mapper.book.BookMapper;

@Component
public class OrderDetailsMapper extends AbstractMapper<OrderDetails, BookInOrderDTO> {
    private BookMapper bookMapper;

    public OrderDetailsMapper() {
        super(OrderDetails.class, BookInOrderDTO.class);
    }

    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public BookInOrderDTO toDTO(OrderDetails orderDetails) {
        BookInOrderDTO bookInOrderDTO = new BookInOrderDTO();
        bookInOrderDTO.setCount(orderDetails.getCount());
        bookInOrderDTO.setBook(bookMapper.toDTO(orderDetails.getPk().getBook()));
        return bookInOrderDTO;
    }
}
