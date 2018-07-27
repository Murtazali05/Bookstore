package ru.shop.persistense.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartPK implements Serializable {

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    public CartPK(Integer userId, Integer bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public CartPK() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartPK cartPK = (CartPK) o;
        return Objects.equals(userId, cartPK.userId) &&
                Objects.equals(bookId, cartPK.bookId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, bookId);
    }
}
