package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Publisher;
import com.mycompany.myapp.repository.AuthorRepository;
import com.mycompany.myapp.repository.BookRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.mycompany.myapp.repository.PublisherRepository;
import com.mycompany.myapp.service.dto.BookCriteria;
import com.mycompany.myapp.service.dto.CreateBookDTO;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Book}.
 */
@Service
@Transactional
public class BookService {

    private final Logger log = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * Save a book.
     *
     * @param book the entity to save.
     * @return the persisted entity.
     */
    public Book save(CreateBookDTO book) {
        log.debug("Request to save Book : {}", book);

        bookRepository.getBookByIsbn(book.getIsbn())
            .ifPresent(b -> {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Book with ISBN " + book.getIsbn() + " already exists");
            });

        Book newBook = new Book();
        newBook.setIsbn(book.getIsbn());
        newBook.setName(book.getName());
        newBook.setPublishYear(book.getPublishYear());
        newBook.setCopies(book.getCopies());

        Publisher bookPublisher = publisherRepository
            .findById(book.getPublisherId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect publisher ID"));

        newBook.setPublisher(bookPublisher);

        Set<Author> authors = new HashSet<>();

        for (Long authorId : book.getAuthorsId()) {
            Author author = authorRepository
                .findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with ID " + authorId + " not found"));
            authors.add(author);
        }

        newBook.setAuthors(authors);

        newBook = bookRepository.save(newBook);
        return newBook;
    }

    /**
     * Update a book.
     *
     * @param book the entity to save.
     * @return the persisted entity.
     */
    public Book update(Book book) {
        log.debug("Request to update Book : {}", book);
        return bookRepository.save(book);
    }

    /**
     * Partially update a book.
     *
     * @param book the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Book> partialUpdate(String isbn, Book book) {
        log.debug("Request to partially update Book : {}", book);

        return bookRepository
            .findById(isbn)
            .map(existingBook -> {
                if (book.getName() != null) {
                    existingBook.setName(book.getName());
                }
                if (book.getPublishYear() != null) {
                    existingBook.setPublishYear(book.getPublishYear());
                }
                if (book.getCopies() != null) {
                    existingBook.setCopies(book.getCopies());
                }
                if (book.getPicture() != null) {
                    existingBook.setPicture(book.getPicture());
                }
                if (book.getPictureContentType() != null) {
                    existingBook.setPictureContentType(book.getPictureContentType());
                }

                return existingBook;
            })
            .map(bookRepository::save);
    }

    /**
     * Get all the books.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Book> findAll(Pageable pageable) {
        log.debug("Request to get all Books");
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Book> findAllWithCriteria(BookCriteria criteria, Pageable pageable) {
        log.debug("Request to get all Books");
        final Specification<Book> specification = createSpecification(criteria);
        return bookRepository.findAll(specification, pageable);
    }

    /**
     * Get one book by id.
     *
     * @param isbn the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Book> findOne(String isbn) {
        log.debug("Request to get Book : {}", isbn);
        return bookRepository.getBookByIsbn(isbn);
    }

    /**
     * Delete the book by id.
     *
     * @param isbn the id of the entity.
     */
    public void delete(String isbn) {
        log.debug("Request to delete Book : {}", isbn);
        bookRepository.deleteById(isbn);
    }

    protected Specification<Book> createSpecification(BookCriteria criteria) {
        Specification<Book> specification = Specification.where(null);

        if (criteria.getName() != null) {
            specification = specification.and(
                (root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%")
            );
        }

        if (criteria.getMinCopies() != null) {
            specification = specification.and(
                (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("copies"), criteria.getMinCopies())
            );
        }

        if (criteria.getMaxCopies() != null) {
            specification = specification.and(
                (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("copies"), criteria.getMaxCopies())
            );
        }

        if (criteria.getPublisherName() != null) {
            specification = specification.and(
                (root, query, criteriaBuilder) -> {
                    Join<Book, Publisher> publisherJoin = root.join("publisher", JoinType.LEFT);
                    return criteriaBuilder.like(
                        criteriaBuilder.lower(publisherJoin.get("name")),
                        "%" + criteria.getPublisherName().toLowerCase() + "%"
                    );
                }
            );
        }

        return  specification;
    }
}
