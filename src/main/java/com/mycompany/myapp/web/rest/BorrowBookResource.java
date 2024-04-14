package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BorrowedBook;
import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.repository.BookRepository;
import com.mycompany.myapp.repository.BorrowBookRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.BorrowBookService;
import com.mycompany.myapp.service.ClientService;
import com.mycompany.myapp.service.dto.CreateBorrowBookDTO;
import com.mycompany.myapp.service.dto.CreateClientDTO;
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
@RequestMapping("/api/borrow-book")
@Transactional
public class BorrowBookResource {

    private final BorrowBookService borrowBookService;

    public BorrowBookResource(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Void> borrowBook(@Valid @RequestBody CreateBorrowBookDTO borrowBookDTO) {
        borrowBookService.createBorrowBook(borrowBookDTO);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Void> returnBorrowedBook(@PathVariable("id") Integer id) {
        BorrowedBook borrowedBook = borrowBookService
            .getById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect Borrowed book ID!"));

        if (borrowedBook.getReturnDate() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book is already returned!");
        }

        borrowBookService.returnBorrowedBook(borrowedBook.getBook().getIsbn(), borrowedBook.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<BorrowedBook>> getAllBorrowedBooks() {
        List<BorrowedBook> allBorrowedBooks = borrowBookService.getAllBorrowedBooks();

        return ResponseEntity.ok().body(allBorrowedBooks);
    }
}
