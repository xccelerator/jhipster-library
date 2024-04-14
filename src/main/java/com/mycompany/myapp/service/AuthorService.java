package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Publisher;
import com.mycompany.myapp.repository.AuthorRepository;
import com.mycompany.myapp.repository.PublisherRepository;
import com.mycompany.myapp.service.dto.CreateAuthorDTO;
import com.mycompany.myapp.service.dto.CreatePublisherDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void createNewAuthor(CreateAuthorDTO authorDTO) {
        Author newAuthor = new Author();
        newAuthor.setFirstName(authorDTO.getFirstName());
        newAuthor.setLastName(authorDTO.getLastName());
        authorRepository.save(newAuthor);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect author Id"));
    }
}
