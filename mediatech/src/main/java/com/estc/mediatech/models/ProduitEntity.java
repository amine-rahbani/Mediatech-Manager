package com.estc.mediatech.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore; // <--- IMPORT THIS

@Entity
@Table(name = "produit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ref;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private BigDecimal prix;

    @Column(nullable = false)
    private double quantite_stock;

    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore // <--- STOPS THE INFINITE LOOP
    private List<LigneFactureEntity> ligneFactures;
}