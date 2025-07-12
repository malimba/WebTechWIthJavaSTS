package com.example.musicplayer.ws.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.musicplayer.ws.io.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    Optional<UserEntity> findByEmail(String email);

}
