package com.estc.mediatech.web;

import com.estc.mediatech.models.ClientEntity;
import com.estc.mediatech.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*") // Allows Angular to connect
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientEntity> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientEntity getOne(@PathVariable Integer id) {
        return clientService.getById(id).orElse(null);
    }

    @PostMapping
    public ClientEntity save(@RequestBody ClientEntity client) {
        return clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.delete(id);
    }
}