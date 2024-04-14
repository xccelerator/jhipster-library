package com.mycompany.myapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A publisher.
 */
@Entity
@Table(name = "borrowed_book")
public class BorrowedBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_isbn")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NotNull
    @Column(name = "borrow_date", nullable = false)
    private Date borrowDate = new Date();

    @Column(name = "return_date", nullable = false)
    private Date returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBook that = (BorrowedBook) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(book, that.book) &&
            Objects.equals(client, that.client) &&
            Objects.equals(borrowDate, that.borrowDate) &&
            Objects.equals(returnDate, that.returnDate)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, client, borrowDate, returnDate);
    }

    @Override
    public String toString() {
        return (
            "BorrowedBook{" +
            "id=" +
            id +
            ", book=" +
            book +
            ", client=" +
            client +
            ", borrowDate=" +
            borrowDate +
            ", returnDate=" +
            returnDate +
            '}'
        );
    }
}
