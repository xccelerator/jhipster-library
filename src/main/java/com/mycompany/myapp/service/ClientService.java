package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.repository.AuthorRepository;
import com.mycompany.myapp.repository.ClientRepository;
import com.mycompany.myapp.service.dto.CreateAuthorDTO;
import com.mycompany.myapp.service.dto.CreateClientDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createNewClient(CreateClientDTO clientDTO) {
        Client newClient = new Client();
        newClient.setFirstName(clientDTO.getFirstName());
        newClient.setLastName(clientDTO.getLastName());
        newClient.setAddress(clientDTO.getAddress());
        newClient.setPhone(clientDTO.getPhone());
        clientRepository.save(newClient);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Integer id) {
        return clientRepository.findById(id);
    }
}
