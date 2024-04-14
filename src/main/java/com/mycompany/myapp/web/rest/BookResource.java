package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Publisher;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.BookService;
import com.mycompany.myapp.service.PublisherService;
import com.mycompany.myapp.service.dto.CreateBookDTO;
import com.mycompany.myapp.service.dto.CreatePublisherDTO;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/book")
@Transactional
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> createNewBook(@Valid @RequestBody CreateBookDTO bookDTO) {
        bookService.createNewBook(bookDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn) {
        Book bookByIsbn = bookService
            .getBookByIsbn(isbn)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect ISBN."));

        return ResponseEntity.ok().body(bookByIsbn);
    }

    @DeleteMapping("/{isbn}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteBookById(@PathVariable("isbn") String isbn) {
        bookService.getBookByIsbn(isbn).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect ISBN."));

        bookService.deleteBookByIsbn(isbn);
        return ResponseEntity.ok().build();
    }
}
