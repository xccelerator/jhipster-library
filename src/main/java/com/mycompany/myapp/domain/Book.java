package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false)
    private String isbn;

    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;

    @NotNull
    @Column(name = "publish_year", nullable = false)
    private String publishYear;

    @NotNull
    @Column(nullable = false)
    private int copies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_isbn"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BorrowedBook> borrowedBooks;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
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
        Book book = (Book) o;
        return (
            copies == book.copies &&
            Objects.equals(isbn, book.isbn) &&
            Objects.equals(name, book.name) &&
            Objects.equals(publisher, book.publisher) &&
            Objects.equals(publishYear, book.publishYear) &&
            Objects.equals(authors, book.authors) &&
            Objects.equals(borrowedBooks, book.borrowedBooks)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, name, publisher, publishYear, copies, authors, borrowedBooks);
    }

    @Override
    public String toString() {
        return (
            "Book{" +
            "isbn='" +
            isbn +
            '\'' +
            ", name='" +
            name +
            '\'' +
            ", publisher=" +
            publisher +
            ", publishYear='" +
            publishYear +
            '\'' +
            ", copies=" +
            copies +
            ", authors=" +
            authors +
            ", borrowedBooks=" +
            borrowedBooks +
            '}'
        );
    }
}
