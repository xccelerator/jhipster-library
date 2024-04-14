package com.mycompany.myapp.service;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Publisher;
import com.mycompany.myapp.repository.AuthorRepository;
import com.mycompany.myapp.repository.BookRepository;
import com.mycompany.myapp.repository.PublisherRepository;
import com.mycompany.myapp.service.dto.CreateBookDTO;
import com.mycompany.myapp.service.dto.CreatePublisherDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import io.undertow.util.BadRequestException;
import jakarta.persistence.EntityExistsException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class BookService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(PublisherRepository publisherRepository, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void createNewBook(CreateBookDTO bookDTO) {
        Optional<Book> bookByIsbn = bookRepository.getBookByIsbn(bookDTO.getIsbn());

        if (bookByIsbn.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book with that ISBN already exist!");
        }

        Book newBook = new Book();
        newBook.setIsbn(bookDTO.getIsbn());
        newBook.setName(bookDTO.getName());
        newBook.setPublishYear(bookDTO.getPublishYear());
        newBook.setCopies(bookDTO.getCopies());

        Publisher bookPublisher = publisherRepository
            .findById(bookDTO.getPublisherId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect publisher ID"));

        newBook.setPublisher(bookPublisher);

        Set<Author> authors = new HashSet<>();

        for (Long authorId : bookDTO.getAuthorsId()) {
            Author author = authorRepository
                .findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with ID " + authorId + " not found"));
            authors.add(author);
        }

        newBook.setAuthors(authors);

        bookRepository.save(newBook);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookByIsbn(String Isbn) {
        return bookRepository.getBookByIsbn(Isbn);
    }

    public void deleteBookByIsbn(String Isbn) {
        bookRepository.deleteById(Isbn);
    }
}
