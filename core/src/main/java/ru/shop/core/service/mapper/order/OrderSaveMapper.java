package ru.shop.core.service.mapper.order;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Order;
import ru.shop.core.service.dto.order.OrderSaveDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class OrderSaveMapper extends AbstractMapper<Order, OrderSaveDTO> {

    public OrderSaveMapper() {
        super(Order.class, OrderSaveDTO.class);
    }

}
