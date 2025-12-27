package com.estc.mediatech.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Entity
@Table(name = "facture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false, name="date_creation_facture")
    private Date date;

    // Link to the Client
    @ManyToOne
    private ClientEntity client;

    // Link to the Lines (Products + Quantities)
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // <--- STOPS THE INFINITE LOOP
    private List<LigneFactureEntity> ligneFactures;
}