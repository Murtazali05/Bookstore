package ru.shop.persistense.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_details", schema = "shop", catalog = "bookstore")
@AssociationOverrides({
        @AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "book_id"))
})
public class OrderDetails {
    @EmbeddedId
    private OrderDetailsPK pk;

    private int count;

    public OrderDetails() {
        this.pk = new OrderDetailsPK();
    }

    public OrderDetails(OrderDetailsPK pk) {
        this.pk = pk;
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
                Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pk, count);
    }

    public Order getOrder() {
        return pk.getOrder();
    }

    public void setOrder(Order order) {
        pk.setOrder(order);
    }

    public Book getBook() {
        return pk.getBook();
    }

    public void setBook(Book book) {
        pk.setBook(book);
    }
}
