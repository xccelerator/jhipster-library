package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.repository.BookRepository;
import com.mycompany.myapp.service.BookService;
import com.mycompany.myapp.service.dto.BookCriteria;
import com.mycompany.myapp.service.dto.CreateBookDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Book}.
 */
@RestController
@RequestMapping("/api/books")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private static final String ENTITY_NAME = "book";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * {@code POST  /books} : Create a new book.
     *
     * @param book the book to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new book, or with status {@code 400 (Bad Request)} if the book has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Book> createBook(@Valid @RequestBody CreateBookDTO book) throws URISyntaxException {
        log.debug("REST request to save Book : {}", book);
        Book newBook = bookService.save(book);
        return ResponseEntity.created(new URI("/api/books/" + newBook.getIsbn()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, newBook.getIsbn().toString()))
            .body(newBook);
    }

    /**
     * {@code PUT  /books/:isbn} : Updates an existing book.
     *
     * @param isbn the isbn of the book to save.
     * @param book the book to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "isbn", required = false) final String isbn, @Valid @RequestBody Book book)
        throws URISyntaxException {
        log.debug("REST request to update Book : {}, {}", isbn, book);

        book = bookService.update(book);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, isbn))
            .body(book);
    }

    /**
     * {@code PATCH  /books/:isbn} : Partial updates given fields of an existing book, field will ignore if it is null
     *
     * @param isbn the isbn of the book to save.
     * @param book the book to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 404 (Not Found)} if the book is not found,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{isbn}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Book> partialUpdateBook(
        @PathVariable(value = "isbn", required = false) final String isbn,
        @NotNull @RequestBody Book book
    ) throws URISyntaxException {
        log.debug("REST request to partial update Book partially : {}, {}", isbn, book);

        Optional<Book> result = bookService.partialUpdate(isbn, book);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, isbn)
        );
    }

    /**
     * {@code GET  /books} : get all the books.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of books in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks(@ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Books");
        Page<Book> page = bookService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
//
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getAllBooksWithCriteria(@ParameterObject BookCriteria criteria, @ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Books");
        Page<Book> page = bookService.findAllWithCriteria(criteria, pageable);
//        return ResponseEntity.ok().body(page);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /books/:isbn} : get the "isbn" book.
     *
     * @param isbn the isbn of the book to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the book, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBook(@PathVariable("isbn") String isbn) {
        log.debug("REST request to get Book : {}", isbn);
        Optional<Book> book = bookService.findOne(isbn);
        return ResponseUtil.wrapOrNotFound(book);
    }

    /**
     * {@code DELETE  /books/:isbn} : delete the "isbn" book.
     *
     * @param isbn the isbn of the book to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable("isbn") String isbn) {
        log.debug("REST request to delete Book : {}", isbn);
        bookService.delete(isbn);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, isbn.toString()))
            .build();
    }
}
