package ru.shop.persistense.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "book", schema = "shop", catalog = "bookstore")
public class Book {
    private int id;
    private String title;
    private String description;
    private Date pubyear;
    private BigDecimal price;
    private Photo photo;
    private Date createdAt = new Date(System.currentTimeMillis());
    private Collection<Cart> carts;
    private Collection<OrderDetails> orderDetails;

    private Collection<Author> authors;
    private Collection<Category> categories;
    private Collection<Genre> genres;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 65535, columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "pubyear", nullable = false)
    public Date getPubyear() {
        return pubyear;
    }

    public void setPubyear(Date pubyear) {
        this.pubyear = pubyear;
    }

    @Basic
    @Column(name = "price", columnDefinition = "money", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return id == that.id &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(pubyear, that.pubyear) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, pubyear, price);
    }

    @ManyToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id", nullable = false)
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @OneToMany(mappedBy = "book")
    public Collection<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Collection<Cart> carts) {
        this.carts = carts;
    }

    @OneToMany(mappedBy = "book")
    public Collection<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    public Collection<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    @ManyToMany
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    @ManyToMany
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    public Collection<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
    }
}
