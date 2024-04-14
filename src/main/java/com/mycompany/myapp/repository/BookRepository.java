package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Book;
import com.mycompany.myapp.domain.Publisher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> getBookByIsbn(String isbn);

    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies - 1 WHERE b.isbn = :isbn AND b.copies > 0")
    Integer reduceCopiesByOne(String isbn);

    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + 1 WHERE b.isbn = :isbn")
    Integer increaseCopiesByOne(String isbn);

    @Query("SELECT b.copies FROM Book b WHERE b.isbn = :isbn")
    Integer copiesNumber(String isbn);
}
