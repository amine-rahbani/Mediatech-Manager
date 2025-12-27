package com.estc.mediatech.service;

import com.estc.mediatech.models.ProduitEntity;
import com.estc.mediatech.Repositories.ProduitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitDao produitDao;

    // Add or Update Product
    public ProduitEntity save(ProduitEntity produit) {
        return produitDao.save(produit);
    }

    // Get all Products (Inventory)
    public List<ProduitEntity> getAll() {
        return produitDao.findAll();
    }

    // Get one Product
    public Optional<ProduitEntity> getById(Integer id) {
        return produitDao.findById(id);
    }

    // Delete Product
    public void delete(Integer id) {
        produitDao.deleteById(id);
    }
}