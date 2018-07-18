package ru.shop.service.dto.cart;

import java.util.List;

public class CartDTO {
    private int userId;
    private List<BookInCartDTO> book;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<BookInCartDTO> getBook() {
        return book;
    }

    public void setBook(List<BookInCartDTO> book) {
        this.book = book;
    }
}
