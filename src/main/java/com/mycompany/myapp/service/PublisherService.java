package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Publisher;
import com.mycompany.myapp.repository.PublisherRepository;
import com.mycompany.myapp.service.dto.CreatePublisherDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void createNewPublisher(CreatePublisherDTO publisherDTO) {
        Publisher newPublisher = new Publisher();
        newPublisher.setName(publisherDTO.getName());
        publisherRepository.save(newPublisher);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }
}
