package com.estc.mediatech.Repositories; // Matches your folder name

import com.estc.mediatech.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<ClientEntity, Integer> {
    java.util.Optional<ClientEntity> findByClientUser_Username(String username);
}