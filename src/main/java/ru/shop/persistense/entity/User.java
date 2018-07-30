package ru.shop.persistense.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "shop", catalog = "bookstore")
public class User {
    private int id;
    private String surname;
    private String name;
    private String email;
    private String password;
    private Date birthday;
    private Boolean confirmation;
    private String confirmCode;
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());
    private Role role;
    private Photo photo;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

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
    @Column(name = "surname", nullable = false, length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 55)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "confirmation")
    public Boolean getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Boolean confirmation) {
        this.confirmation = confirmation;
    }

    @Basic
    @Column(name = "confirm_code", length = 65535, columnDefinition = "text")
    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    @Basic
    @Column(name = "registration_date")
    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id", nullable = true)
    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
