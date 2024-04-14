package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {}
