package ru.shop.service.mapper.order;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.OrderDetails;
import ru.shop.service.dto.order.BookInOrderDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class OrderDetailsMapper extends AbstractMapper<OrderDetails, BookInOrderDTO> {

    public OrderDetailsMapper() {
        super(OrderDetails.class, BookInOrderDTO.class);
    }

}
