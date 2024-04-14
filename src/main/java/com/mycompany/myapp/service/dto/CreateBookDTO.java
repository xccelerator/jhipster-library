package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Set;

public class CreateBookDTO implements Serializable {

    private String isbn;
    private String name;
    private Set<Long> authorsId;
    private Long publisherId;
    private String publishYear;
    private Integer copies;

    public Set<Long> getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(Set<Long> authorsId) {
        this.authorsId = authorsId;
    }

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

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
}
