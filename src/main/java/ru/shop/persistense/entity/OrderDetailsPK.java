package ru.shop.persistense.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailsPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    public OrderDetailsPK() {
    }

    public OrderDetailsPK(Order order, Book book) {
        this.order = order;
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsPK that = (OrderDetailsPK) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(order, book);
    }
}
