package ru.shop.persistense.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartPK implements Serializable {
    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    public CartPK() {
    }

    public CartPK(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        CartPK cartPK = (CartPK) o;
        return Objects.equals(user, cartPK.user) &&
                Objects.equals(book, cartPK.book);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, book);
    }
}
