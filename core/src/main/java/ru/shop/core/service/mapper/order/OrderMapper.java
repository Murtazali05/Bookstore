package ru.shop.core.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Order;
import ru.shop.core.persistense.entity.OrderDetails;
import ru.shop.core.service.dto.order.BookInOrderDTO;
import ru.shop.core.service.dto.order.OrderDTO;
import ru.shop.core.service.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDTO> {
    private final StatusMapper statusMapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @Autowired
    public OrderMapper(StatusMapper statusMapper, OrderDetailsMapper orderDetailsMapper) {
        super(Order.class, OrderDTO.class);
        this.statusMapper = statusMapper;
        this.orderDetailsMapper = orderDetailsMapper;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setDeliveryMethod(order.getDeliveryMethod());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setStatus(statusMapper.toDTO(order.getStatus()));

        List<OrderDetails> orderDetailsList = (List<OrderDetails>) order.getOrderDetails();
        List<BookInOrderDTO> bookInOrderDTOList = new ArrayList<>();
        for (OrderDetails orderDetails : orderDetailsList){
            bookInOrderDTOList.add(orderDetailsMapper.toDTO(orderDetails));
        }
        orderDTO.setOrderDetails(bookInOrderDTOList);
        return orderDTO;
    }
}
