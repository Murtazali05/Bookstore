package ru.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.entity.Cart;
import ru.shop.persistense.entity.Order;
import ru.shop.persistense.entity.OrderDetails;
import ru.shop.persistense.entity.User;
import ru.shop.persistense.repository.OrderRepository;
import ru.shop.persistense.repository.StatusRepository;
import ru.shop.persistense.repository.UserRepository;
import ru.shop.service.dto.order.OrderDTO;
import ru.shop.service.dto.order.OrderSaveDTO;
import ru.shop.service.mapper.order.OrderMapper;
import ru.shop.service.mapper.order.OrderSaveMapper;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderSaveMapper orderSaveMapper;
    private OrderMapper orderMapper;

    private UserRepository userRepository;
    private StatusRepository statusRepository;
    private CartService cartService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setOrderSaveMapper(OrderSaveMapper orderSaveMapper) {
        this.orderSaveMapper = orderSaveMapper;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setStatusRepository(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Transactional(readOnly = true)
    public OrderDTO getOrder(Integer id) throws NotFoundException {
        if (!orderRepository.existsById(id))
            throw new NotFoundException("Order with such id=" + id + " does not found!");

//        Order order = orderRepository.getOne(id);

        Order order = orderRepository.findOneById(id);
        return orderMapper.toDTO(order);
    }

    @Transactional
    public OrderDTO create(OrderSaveDTO orderDTO, Integer userId) {
        User user = userRepository.getOne(userId);
        Collection<Cart> carts = user.getCarts();

        if (carts == null || carts.isEmpty())
            throw new IllegalStateException("Cart is empty!");

        Order order = orderSaveMapper.toEntity(orderDTO);
        order.setUser(user);
        order.setStatus(statusRepository.findByCode("PROCESSING"));

        Order orderWithID = orderRepository.save(order);
        Collection<OrderDetails> orderDetailsCollection = new ArrayList<>();
        for (Cart cart: carts){
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setBook(cart.getBook());
            orderDetails.setOrder(orderWithID);
            orderDetails.setCount(cart.getCount());
            orderDetailsCollection.add(orderDetails);
        }

        orderWithID.setOrderDetails(orderDetailsCollection);
        cartService.clear(userId);
        return orderMapper.toDTO(orderWithID);
    }

}
