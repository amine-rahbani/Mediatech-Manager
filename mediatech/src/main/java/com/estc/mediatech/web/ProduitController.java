package com.estc.mediatech.web;

import com.estc.mediatech.models.ProduitEntity;
import com.estc.mediatech.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin("*")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<ProduitEntity> getAll() {
        return produitService.getAll();
    }

    @GetMapping("/{id}")
    public ProduitEntity getOne(@PathVariable Integer id) {
        return produitService.getById(id).orElse(null);
    }

    @PostMapping
    public ProduitEntity save(@RequestBody ProduitEntity produit) {
        return produitService.save(produit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        produitService.delete(id);
    }
}