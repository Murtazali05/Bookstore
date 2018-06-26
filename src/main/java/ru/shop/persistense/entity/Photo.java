package ru.shop.persistense.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "photo", schema = "shop", catalog = "bookstore")
public class Photo {
    private int id;
    private String path;
    private Integer size;
    private Timestamp uploadedDate = new Timestamp(System.currentTimeMillis());
    private String hashFile;
    private Collection<Author> authors;
    private Collection<Book> books;
    private Collection<User> users;

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
    @Column(name = "path", nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "size", nullable = true)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "uploaded_date", nullable = true)
    public Timestamp getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Timestamp uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    @Basic
    @Column(name = "hash_file", nullable = true)
    public String getHashFile() {
        return hashFile;
    }

    public void setHashFile(String hashFile) {
        this.hashFile = hashFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo that = (Photo) o;
        return id == that.id &&
                Objects.equals(path, that.path) &&
                Objects.equals(size, that.size) &&
                Objects.equals(uploadedDate, that.uploadedDate) &&
                Objects.equals(hashFile, that.hashFile);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, path, size, uploadedDate, hashFile);
    }

    @OneToMany(mappedBy = "photo")
    public Collection<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    @OneToMany(mappedBy = "photo")
    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @OneToMany(mappedBy = "photo")
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
