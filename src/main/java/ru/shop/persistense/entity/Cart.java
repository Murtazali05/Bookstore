package ru.shop.persistense.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart", schema = "shop", catalog = "bookstore")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "book_id"))
})
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

    @Transient
    public User getUser() {
        return pk.getUser();
    }

    public void setUser(User user) {
        pk.setUser(user);
    }

    @Transient
    public Book getBook() {
        return pk.getBook();
    }

    public void setBook(Book book) {
        pk.setBook(book);
    }
}
