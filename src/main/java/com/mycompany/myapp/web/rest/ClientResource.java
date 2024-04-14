package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Author;
import com.mycompany.myapp.domain.Client;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.AuthorService;
import com.mycompany.myapp.service.BorrowBookService;
import com.mycompany.myapp.service.ClientService;
import com.mycompany.myapp.service.dto.CreateAuthorDTO;
import com.mycompany.myapp.service.dto.CreateBorrowBookDTO;
import com.mycompany.myapp.service.dto.CreateClientDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/client")
@Transactional
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Void> createNewClient(@Valid @RequestBody CreateClientDTO clientDTO) {
        clientService.createNewClient(clientDTO);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")" + "|| hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
        Client client = clientService
            .getClientById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Incorrect client ID"));

        return ResponseEntity.ok().body(client);
    }
}
