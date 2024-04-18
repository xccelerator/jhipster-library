package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO representing a user, with only the public attributes.
 */
public class CreateBookDTO implements Serializable {

    private String isbn;
    private String name;
    private Set<Long> authorsId;
    private Long publisherId;
    private Instant publishYear;
    private Integer copies;

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

    public Set<Long> getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(Set<Long> authorsId) {
        this.authorsId = authorsId;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Instant getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Instant publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateBookDTO that = (CreateBookDTO) o;
        return Objects.equals(isbn, that.isbn) && Objects.equals(name, that.name) && Objects.equals(authorsId, that.authorsId) && Objects.equals(publisherId, that.publisherId) && Objects.equals(publishYear, that.publishYear) && Objects.equals(copies, that.copies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, name, authorsId, publisherId, publishYear, copies);
    }

    @Override
    public String toString() {
        return "CreateBookDTO{" +
            "isbn='" + isbn + '\'' +
            ", name='" + name + '\'' +
            ", authorsId=" + authorsId +
            ", publisherId=" + publisherId +
            ", publishYear=" + publishYear +
            ", copies=" + copies +
            '}';
    }
}
