package com.indoxxii.indoxxii.presist.repositories;

import java.util.Optional;

import com.indoxxii.indoxxii.presist.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
    
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
