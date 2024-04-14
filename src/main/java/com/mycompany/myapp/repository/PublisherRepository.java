package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {}
