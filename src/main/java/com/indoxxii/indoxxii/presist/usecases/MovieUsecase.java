package com.indoxxii.indoxxii.presist.usecases;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.indoxxii.indoxxii.applications.request.movie.MovieCreateDto;
import com.indoxxii.indoxxii.applications.request.movie.MovieUpdateDto;
import com.indoxxii.indoxxii.presist.models.Movie;
import com.indoxxii.indoxxii.presist.repositories.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieUsecase {
    @Autowired
    MovieRepository movieRepository;

    public Movie findById(Integer idmovies) {
        Optional<Movie> optionalMovie = movieRepository.findById(idmovies);
        Movie movie = optionalMovie.get();

        return movie;
    }
    public Set<Movie> findAll() {
        List<Movie> listMovie = movieRepository.findAll();

        return new HashSet<Movie>(listMovie);
    }
    public Movie create(MovieCreateDto dto) throws Exception {
        
        var newMovie=new Movie();
        newMovie.setTitle(dto.getTitle());
        newMovie.setPosterdirectory(dto.getPosterdirectory());
        newMovie.setSynopsis(dto.getSynopsis());
        newMovie.setRating(dto.getRating());
        newMovie.setPrice(dto.getPrice());
        return movieRepository.save(newMovie);
    }
    public Movie update(@Valid MovieUpdateDto dto, Integer idmovies) throws Exception{
        Optional<Movie> optionalMovie = movieRepository.findById(idmovies);
        var movie = optionalMovie.get();
        movie.setTitle(dto.getTitle());
        movie.setPosterdirectory(dto.getPosterdirectory());
        movie.setSynopsis(dto.getSynopsis());
        movie.setRating(dto.getRating());
        movie.setPrice(dto.getPrice());
        return movieRepository.save(movie);
    }
}
