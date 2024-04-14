package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.BorrowedBook;
import com.mycompany.myapp.domain.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowedBook, Integer> {
    Optional<BorrowedBook> getBorrowedBookByBookAndClient(Book book, Client client);

    @Modifying
    @Query("UPDATE BorrowedBook bb SET bb.returnDate = CURRENT_TIMESTAMP WHERE bb.id = :id")
    Integer updateReturnDateById(Integer id);
}
