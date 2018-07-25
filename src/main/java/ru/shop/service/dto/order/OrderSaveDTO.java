package ru.shop.service.dto.order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OrderSaveDTO {
    @NotBlank
    @Size(min = 5, max = 500)
    private String address;

    @NotBlank
    @Size(min = 5, max = 55)
    private String deliveryMethod;

    @NotBlank
    @Size(min = 5, max = 55)
    private String paymentMethod;

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
}
