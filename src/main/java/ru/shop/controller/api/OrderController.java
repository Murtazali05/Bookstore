package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shop.security.UserDetailsImpl;
import ru.shop.service.OrderService;
import ru.shop.service.dto.order.OrderDTO;
import ru.shop.service.dto.order.OrderSaveDTO;
import ru.shop.service.dto.order.StatusEnum;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Api(tags = "Orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getOrdersByStatus(StatusEnum status){
        return orderService.getOrders(status.name());
    }

    @GetMapping("/{id}")
    public OrderDTO get(@PathVariable Integer id) throws NotFoundException {
        return orderService.getOrder(id);
    }

    @PostMapping
    public OrderDTO create(@Valid @RequestBody OrderSaveDTO orderDTO, @AuthenticationPrincipal UserDetailsImpl user){
        return orderService.create(orderDTO, user.getId());
    }

    @PutMapping("/{id}")
    public OrderDTO setStatus(@PathVariable("id") Integer id, StatusEnum status) throws NotFoundException {
        return orderService.setStatus(id, status.name());
    }

    @DeleteMapping("/{id}")
    public OrderDTO delete(@PathVariable("id") Integer id){
        return orderService.delete(id);
    }
}
