package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BorrowedBook;
import com.mycompany.myapp.repository.BorrowedBookRepository;
import com.mycompany.myapp.service.BorrowedBookService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.BorrowedBook}.
 */
@RestController
@RequestMapping("/api/borrowed-books")
public class BorrowedBookResource {

    private final Logger log = LoggerFactory.getLogger(BorrowedBookResource.class);

    private static final String ENTITY_NAME = "borrowedBook";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BorrowedBookService borrowedBookService;

    private final BorrowedBookRepository borrowedBookRepository;

    public BorrowedBookResource(BorrowedBookService borrowedBookService, BorrowedBookRepository borrowedBookRepository) {
        this.borrowedBookService = borrowedBookService;
        this.borrowedBookRepository = borrowedBookRepository;
    }

    /**
     * {@code POST  /borrowed-books} : Create a new borrowedBook.
     *
     * @param borrowedBook the borrowedBook to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new borrowedBook, or with status {@code 400 (Bad Request)} if the borrowedBook has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BorrowedBook> createBorrowedBook(@RequestBody BorrowedBook borrowedBook) throws URISyntaxException {
        log.debug("REST request to save BorrowedBook : {}", borrowedBook);
        if (borrowedBook.getId() != null) {
            throw new BadRequestAlertException("A new borrowedBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        borrowedBook = borrowedBookService.save(borrowedBook);
        return ResponseEntity.created(new URI("/api/borrowed-books/" + borrowedBook.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, borrowedBook.getId().toString()))
            .body(borrowedBook);
    }

    /**
     * {@code PUT  /borrowed-books/:id} : Updates an existing borrowedBook.
     *
     * @param id the id of the borrowedBook to save.
     * @param borrowedBook the borrowedBook to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated borrowedBook,
     * or with status {@code 400 (Bad Request)} if the borrowedBook is not valid,
     * or with status {@code 500 (Internal Server Error)} if the borrowedBook couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BorrowedBook> updateBorrowedBook(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody BorrowedBook borrowedBook
    ) throws URISyntaxException {
        log.debug("REST request to update BorrowedBook : {}, {}", id, borrowedBook);
        if (borrowedBook.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, borrowedBook.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!borrowedBookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        borrowedBook = borrowedBookService.update(borrowedBook);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, borrowedBook.getId().toString()))
            .body(borrowedBook);
    }

    /**
     * {@code PATCH  /borrowed-books/:id} : Partial updates given fields of an existing borrowedBook, field will ignore if it is null
     *
     * @param id the id of the borrowedBook to save.
     * @param borrowedBook the borrowedBook to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated borrowedBook,
     * or with status {@code 400 (Bad Request)} if the borrowedBook is not valid,
     * or with status {@code 404 (Not Found)} if the borrowedBook is not found,
     * or with status {@code 500 (Internal Server Error)} if the borrowedBook couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BorrowedBook> partialUpdateBorrowedBook(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody BorrowedBook borrowedBook
    ) throws URISyntaxException {
        log.debug("REST request to partial update BorrowedBook partially : {}, {}", id, borrowedBook);
        if (borrowedBook.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, borrowedBook.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!borrowedBookRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BorrowedBook> result = borrowedBookService.partialUpdate(borrowedBook);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, borrowedBook.getId().toString())
        );
    }

    /**
     * {@code GET  /borrowed-books} : get all the borrowedBooks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of borrowedBooks in body.
     */
    @GetMapping("")
    public List<BorrowedBook> getAllBorrowedBooks() {
        log.debug("REST request to get all BorrowedBooks");
        return borrowedBookService.findAll();
    }

    /**
     * {@code GET  /borrowed-books/:id} : get the "id" borrowedBook.
     *
     * @param id the id of the borrowedBook to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the borrowedBook, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BorrowedBook> getBorrowedBook(@PathVariable("id") Integer id) {
        log.debug("REST request to get BorrowedBook : {}", id);
        Optional<BorrowedBook> borrowedBook = borrowedBookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(borrowedBook);
    }

    /**
     * {@code DELETE  /borrowed-books/:id} : delete the "id" borrowedBook.
     *
     * @param id the id of the borrowedBook to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowedBook(@PathVariable("id") Integer id) {
        log.debug("REST request to delete BorrowedBook : {}", id);
        borrowedBookService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
