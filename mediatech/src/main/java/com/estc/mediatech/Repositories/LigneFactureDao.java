package com.estc.mediatech.Repositories;

import com.estc.mediatech.models.LigneFactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneFactureDao extends JpaRepository<LigneFactureEntity, Integer> {
}