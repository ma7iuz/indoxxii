package com.indoxxii.indoxxii.presist.repositories;

import com.indoxxii.indoxxii.presist.models.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    
}
