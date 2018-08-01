package ru.shop.core.service.dto.cart;

import java.util.List;

public class CartDTO {
    private int userId;
    private List<BookInCartDTO> items;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<BookInCartDTO> getItems() {
        return items;
    }

    public void setItems(List<BookInCartDTO> items) {
        this.items = items;
    }
}
