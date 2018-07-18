package ru.shop.service.dto.cart;

import ru.shop.service.dto.book.BookDTO;

public class BookInCartDTO {
    private BookDTO book;
    private int count;

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
