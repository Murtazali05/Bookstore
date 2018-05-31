package ru.shop.persistense.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_details", schema = "shop", catalog = "bookstore")
public class OrderDetails {
    @EmbeddedId
    private OrderDetailsPK pk;

    private int count;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("bookId")
    private Book book;

    public OrderDetails() {
        this.pk = new OrderDetailsPK();
    }

    public OrderDetailsPK getPk() {
        return pk;
    }

    public void setPk(OrderDetailsPK pk) {
        this.pk = pk;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return count == that.count &&
                Objects.equals(pk, that.pk) &&
                Objects.equals(order, that.order) &&
                Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pk, count, order, book);
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
}
