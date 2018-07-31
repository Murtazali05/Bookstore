package ru.shop.service.dto.order;

import java.util.List;

public class OrderDTO {
    private Integer id;
    private String address;
    private String deliveryMethod;
    private String paymentMethod;
    private StatusDTO status;
    private Integer userId;
    private List<BookInOrderDTO> orderDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<BookInOrderDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<BookInOrderDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
