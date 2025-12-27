package com.estc.mediatech.service;

import com.estc.mediatech.Repositories.*;
import com.estc.mediatech.dto.FactureRequest;
import com.estc.mediatech.dto.LigneRequest;
import com.estc.mediatech.models.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FactureService {

    @Autowired
    private FactureDao factureDao;

    @Autowired
    private LigneFactureDao ligneFactureDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private ProduitDao produitDao;

    @Transactional // Ensures stock is only updated if invoice is successfully saved
    public FactureEntity createFacture(FactureRequest request) {
        // 1. Fetch Client
        ClientEntity client = clientDao.findById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // 2. Create and Save Facture
        FactureEntity facture = new FactureEntity();
        facture.setReference("REF-" + UUID.randomUUID().toString().substring(0, 8)); // Generate unique ref
        facture.setDate(new Date());
        facture.setClient(client);
        FactureEntity savedFacture = factureDao.save(facture);

        // 3. Process each line (Product + Quantity)
        for (LigneRequest ligne : request.getLignes()) {
            ProduitEntity produit = produitDao.findById(ligne.getProduitId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // CHECK STOCK
            if (produit.getQuantite_stock() < ligne.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + produit.getLibelle());
            }

            // DEDUCT STOCK
            produit.setQuantite_stock(produit.getQuantite_stock() - ligne.getQuantity());
            produitDao.save(produit);

            // SAVE LIGNE
            LigneFactureEntity ligneEntity = new LigneFactureEntity();
            ligneEntity.setFacture(savedFacture);
            ligneEntity.setProduit(produit);
            ligneEntity.setQuantity(ligne.getQuantity());
            ligneFactureDao.save(ligneEntity);
        }

        return savedFacture;
    }

    @Transactional
    public FactureEntity createFactureForUser(String username, List<LigneRequest> lignes) {
        // 1. Fetch Client by Username
        ClientEntity client = clientDao.findByClientUser_Username(username)
                .orElseThrow(() -> new RuntimeException("Client profile not found for user: " + username));

        // 2. Create and Save Facture
        FactureEntity facture = new FactureEntity();
        facture.setReference("REF-" + UUID.randomUUID().toString().substring(0, 8));
        facture.setDate(new Date());
        facture.setClient(client);
        FactureEntity savedFacture = factureDao.save(facture);

        // 3. Process each line
        for (LigneRequest ligne : lignes) {
            ProduitEntity produit = produitDao.findById(ligne.getProduitId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (produit.getQuantite_stock() < ligne.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + produit.getLibelle());
            }

            produit.setQuantite_stock(produit.getQuantite_stock() - ligne.getQuantity());
            produitDao.save(produit);

            LigneFactureEntity ligneEntity = new LigneFactureEntity();
            ligneEntity.setFacture(savedFacture);
            ligneEntity.setProduit(produit);
            ligneEntity.setQuantity(ligne.getQuantity());
            ligneFactureDao.save(ligneEntity);
        }

        return savedFacture;
    }

    public List<FactureEntity> getAll() {
        return factureDao.findAll();
    }

    public List<FactureEntity> getFacturesByUsername(String username) {
        return factureDao.findByClient_ClientUser_Username(username);
    }

    public void delete(Integer id) {
        factureDao.deleteById(id);
    }
}