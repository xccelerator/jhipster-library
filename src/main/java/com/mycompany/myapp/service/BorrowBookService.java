package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.BorrowedBook;
import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.repository.BookRepository;
import com.mycompany.myapp.repository.BorrowBookRepository;
import com.mycompany.myapp.repository.ClientRepository;
import com.mycompany.myapp.service.dto.CreateBorrowBookDTO;
import com.mycompany.myapp.service.dto.CreateClientDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class BorrowBookService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final BorrowBookRepository borrowBookRepository;

    public BorrowBookService(BookRepository bookRepository, ClientRepository clientRepository, BorrowBookRepository borrowBookRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.borrowBookRepository = borrowBookRepository;
    }

    public void createBorrowBook(CreateBorrowBookDTO borrowBookDTO) {
        BorrowedBook newBorrowBook = new BorrowedBook();

        Book bookByIsbn = bookRepository
            .getBookByIsbn(borrowBookDTO.getBookIsbn())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect book ISBN"));

        Client client = clientRepository
            .findById(borrowBookDTO.getClientId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect client ID"));

        newBorrowBook.setBook(bookByIsbn);
        newBorrowBook.setClient(client);

        Integer copiesNumber = bookRepository.copiesNumber(bookByIsbn.getIsbn());

        if (copiesNumber == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No copies remained");
        }

        bookRepository.reduceCopiesByOne(bookByIsbn.getIsbn());

        borrowBookRepository.save(newBorrowBook);
    }

    public Optional<BorrowedBook> getById(Integer id) {
        return borrowBookRepository.findById(id);
    }

    public void returnBorrowedBook(String bookIsbn, Integer borrowedBookId) {
        bookRepository.increaseCopiesByOne(bookIsbn);
        borrowBookRepository.updateReturnDateById(borrowedBookId);
    }

    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowBookRepository.findAll();
    }
}
