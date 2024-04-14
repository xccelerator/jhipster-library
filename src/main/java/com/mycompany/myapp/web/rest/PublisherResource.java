package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Publisher;
import com.mycompany.myapp.repository.PublisherRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.PublisherService;
import com.mycompany.myapp.service.dto.CreatePublisherDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/publisher")
@Transactional
public class PublisherResource {

    private final PublisherService publisherService;

    public PublisherResource(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public void createNewPublisher(@Valid @RequestBody CreatePublisherDTO publisherDTO) {
        publisherService.createNewPublisher(publisherDTO);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok().body(publishers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable("id") Long id) {
        Publisher publisher = publisherService
            .getPublisherById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect publisher ID!"));

        return ResponseEntity.ok().body(publisher);
    }
}
