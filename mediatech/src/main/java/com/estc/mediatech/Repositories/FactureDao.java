package com.estc.mediatech.Repositories;

import com.estc.mediatech.models.FactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureDao extends JpaRepository<FactureEntity, Integer> {
    java.util.List<FactureEntity> findByClient_ClientUser_Username(String username);
}