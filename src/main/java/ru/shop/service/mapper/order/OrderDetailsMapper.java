package ru.shop.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.OrderDetails;
import ru.shop.service.dto.order.BookInOrderDTO;
import ru.shop.service.mapper.AbstractMapper;
import ru.shop.service.mapper.book.BookMapper;

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
