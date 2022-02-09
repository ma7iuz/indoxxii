package com.indoxxii.indoxxii.presist.repositories;

import com.indoxxii.indoxxii.presist.models.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer>{
    
}
