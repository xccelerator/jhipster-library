package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * A publisher.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<BorrowedBook> borrowedBooks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return (
            Objects.equals(id, client.id) &&
            Objects.equals(firstName, client.firstName) &&
            Objects.equals(lastName, client.lastName) &&
            Objects.equals(address, client.address) &&
            Objects.equals(phone, client.phone) &&
            Objects.equals(borrowedBooks, client.borrowedBooks)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, phone, borrowedBooks);
    }

    @Override
    public String toString() {
        return (
            "Client{" +
            "id=" +
            id +
            ", firstName='" +
            firstName +
            '\'' +
            ", lastName='" +
            lastName +
            '\'' +
            ", address='" +
            address +
            '\'' +
            ", phone='" +
            phone +
            '\'' +
            ", borrowedBooks=" +
            borrowedBooks +
            '}'
        );
    }
}
