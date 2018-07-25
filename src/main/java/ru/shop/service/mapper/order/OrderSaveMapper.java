package ru.shop.service.mapper.order;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Order;
import ru.shop.service.dto.order.OrderSaveDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class OrderSaveMapper extends AbstractMapper<Order, OrderSaveDTO> {

    public OrderSaveMapper() {
        super(Order.class, OrderSaveDTO.class);
    }

}
