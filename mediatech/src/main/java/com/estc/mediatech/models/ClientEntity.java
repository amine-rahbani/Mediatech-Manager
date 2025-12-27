package com.estc.mediatech.models;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore; // <--- IMPORTS THIS

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(name = "client_telephone")
    private String telephone;

    // One Client can have Many Invoices
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // <--- STOPS THE INFINITE LOOP
    private List<FactureEntity> factures;

    @OneToOne
    private UserEntity clientUser;

    public UserEntity getClientUser() {
        return clientUser;
    }

    public void setClientUser(UserEntity clientUser) {
        this.clientUser = clientUser;
    }
}