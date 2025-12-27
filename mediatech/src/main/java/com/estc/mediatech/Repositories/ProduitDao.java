package com.estc.mediatech.Repositories;

import com.estc.mediatech.models.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitDao extends JpaRepository<ProduitEntity, Integer> {
}