package com.estc.mediatech.Repositories;

import com.estc.mediatech.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}