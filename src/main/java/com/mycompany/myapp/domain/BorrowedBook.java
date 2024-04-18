package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * A BorrowedBook.
 */
@Entity
@Table(name = "borrowed_book")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BorrowedBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "borrow_date")
    private Instant borrowDate = new Date().toInstant();

    @Column(name = "updated_date")
    private Instant updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_isbn")
    @JsonIgnoreProperties(value = { "borrowedBooks", "publisher", "authors" }, allowSetters = true)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinet_id")
    @JsonIgnoreProperties(value = { "borrowedBooks" }, allowSetters = true)
    private Client clinet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Instant borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClinet() {
        return clinet;
    }

    public void setClinet(Client clinet) {
        this.clinet = clinet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBook that = (BorrowedBook) o;
        return Objects.equals(id, that.id) && Objects.equals(borrowDate, that.borrowDate) && Objects.equals(updatedDate, that.updatedDate) && Objects.equals(book, that.book) && Objects.equals(clinet, that.clinet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrowDate, updatedDate, book, clinet);
    }

    @Override
    public String toString() {
        return "BorrowedBook{" +
            "id=" + id +
            ", borrowDate=" + borrowDate +
            ", updatedDate=" + updatedDate +
            ", book=" + book +
            ", clinet=" + clinet +
            '}';
    }
}
