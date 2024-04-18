package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO representing a user, with only the public attributes.
 */
public class BookCriteria implements Serializable {
    private String name;
    private String authorName;
    private String publisherName;
    private Instant publishYear;
    private Integer minCopies;
    private Integer maxCopies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Instant getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Instant publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getMinCopies() {
        return minCopies;
    }

    public void setMinCopies(Integer minCopies) {
        this.minCopies = minCopies;
    }

    public Integer getMaxCopies() {
        return maxCopies;
    }

    public void setMaxCopies(Integer maxCopies) {
        this.maxCopies = maxCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCriteria that = (BookCriteria) o;
        return Objects.equals(name, that.name) && Objects.equals(authorName, that.authorName) && Objects.equals(publisherName, that.publisherName) && Objects.equals(publishYear, that.publishYear) && Objects.equals(minCopies, that.minCopies) && Objects.equals(maxCopies, that.maxCopies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, authorName, publisherName, publishYear, minCopies, maxCopies);
    }

    @Override
    public String toString() {
        return "BookCriteria{" +
            "name='" + name + '\'' +
            ", authorName='" + authorName + '\'' +
            ", publisherName='" + publisherName + '\'' +
            ", publishYear=" + publishYear +
            ", minCopies=" + minCopies +
            ", maxCopies=" + maxCopies +
            '}';
    }
}
