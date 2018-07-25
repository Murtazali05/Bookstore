package ru.shop.service.mapper.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Order;
import ru.shop.persistense.entity.OrderDetails;
import ru.shop.service.dto.order.BookInOrderDTO;
import ru.shop.service.dto.order.OrderDTO;
import ru.shop.service.mapper.AbstractMapper;
import ru.shop.service.mapper.user.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDTO> {
    private final UserMapper userMapper;
    private final StatusMapper statusMapper;
    private final OrderDetailsMapper orderDetailsMapper;

    @Autowired
    public OrderMapper(UserMapper userMapper, StatusMapper statusMapper, OrderDetailsMapper orderDetailsMapper) {
        super(Order.class, OrderDTO.class);
        this.userMapper = userMapper;
        this.statusMapper = statusMapper;
        this.orderDetailsMapper = orderDetailsMapper;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUser(userMapper.toDTO(order.getUser()));
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
