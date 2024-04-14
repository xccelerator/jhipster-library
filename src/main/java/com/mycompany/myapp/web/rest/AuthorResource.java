package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Publisher;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.AuthorService;
import com.mycompany.myapp.service.PublisherService;
import com.mycompany.myapp.service.dto.CreateAuthorDTO;
import com.mycompany.myapp.service.dto.CreatePublisherDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/author")
@Transactional
public class AuthorResource {

    private final AuthorService authorService;

    public AuthorResource(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> createNewAuthor(@Valid @RequestBody CreateAuthorDTO authorDTO) {
        authorService.createNewAuthor(authorDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok().body(authors);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
        Author author = authorService.getAuthorById(id);

        return ResponseEntity.ok().body(author);
    }
}
