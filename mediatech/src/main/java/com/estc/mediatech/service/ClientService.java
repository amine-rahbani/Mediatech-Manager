package com.estc.mediatech.service;

import com.estc.mediatech.models.ClientEntity;
import com.estc.mediatech.Repositories.ClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    // Save or Update a Client
    public ClientEntity save(ClientEntity client) {
        return clientDao.save(client);
    }

    // Get all Clients
    public List<ClientEntity> getAll() {
        return clientDao.findAll();
    }

    // Get one Client by ID
    public Optional<ClientEntity> getById(Integer id) {
        return clientDao.findById(id);
    }

    // Delete a Client
    public void delete(Integer id) {
        clientDao.deleteById(id);
    }
}