package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.shop.core.security.UserDetailsImpl;
import ru.shop.core.service.OrderService;
import ru.shop.core.service.dto.order.OrderDTO;
import ru.shop.core.service.dto.order.OrderSaveDTO;
import ru.shop.core.service.enumeration.StatusEnum;

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
    @ApiOperation("Получить список всех заказов")
    public List<OrderDTO> getOrdersByStatus(StatusEnum status){
        return orderService.getOrders(status);
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить информацию о заказе по ID")
    public OrderDTO get(@PathVariable Integer id) throws NotFoundException {
        return orderService.getOrder(id);
    }

    @PostMapping
    @ApiOperation("Оформить заказ")
    public OrderDTO create(@Valid @RequestBody OrderSaveDTO orderDTO, @AuthenticationPrincipal UserDetailsImpl user){
        return orderService.create(orderDTO, user.getId());
    }

    @PutMapping("/{id}")
    @ApiOperation("Изменить статус заказа")
    public OrderDTO setStatus(@PathVariable("id") Integer id, StatusEnum status) throws NotFoundException {
        return orderService.setStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить заказ")
    public OrderDTO delete(@PathVariable("id") Integer id){
        return orderService.delete(id);
    }
}
