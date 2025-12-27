package com.estc.mediatech.models;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ligne_facture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneFactureEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Link to the Invoice
    @ManyToOne
    @JoinColumn(name = "facture_id")
    private FactureEntity facture;

    // Link to the Product
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private ProduitEntity produit;

    // The quantity of this specific product in this specific invoice
    @Column(nullable = false)
    private Double quantity; 
}