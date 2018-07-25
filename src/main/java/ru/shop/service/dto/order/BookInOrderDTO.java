package ru.shop.service.dto.order;

import ru.shop.service.dto.book.BookDTO;

public class BookInOrderDTO {
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