package ru.shop.persistense.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart", schema = "shop", catalog = "bookstore")
public class Cart {
    @EmbeddedId
    private CartPK pk;

    private int count;

    public Cart() {
        pk = new CartPK();
    }

    public Cart(CartPK pk) {
        this.pk = pk;
    }

    public CartPK getPk() {
        return pk;
    }

    public void setPk(CartPK pk) {
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
        Cart cart = (Cart) o;
        return count == cart.count &&
                Objects.equals(pk, cart.pk);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pk, count);
    }
}
