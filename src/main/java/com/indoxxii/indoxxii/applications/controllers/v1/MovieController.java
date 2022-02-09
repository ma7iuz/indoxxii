package com.indoxxii.indoxxii.applications.controllers.v1;

import java.util.Set;

import javax.validation.Valid;

import com.indoxxii.indoxxii.applications.request.movie.MovieCreateDto;
import com.indoxxii.indoxxii.applications.request.movie.MovieUpdateDto;
import com.indoxxii.indoxxii.applications.response.GlobalResponse;
import com.indoxxii.indoxxii.applications.response.Response;
import com.indoxxii.indoxxii.global.Routes;
import com.indoxxii.indoxxii.presist.models.Movie;
import com.indoxxii.indoxxii.presist.usecases.MovieUsecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieUsecase useCase;

    private Response response = new Response();

    @GetMapping(Routes.API_V1 + Routes.MOVIE)
    public ResponseEntity<GlobalResponse> index() {
        Set<Movie> movies=useCase.findAll();
        return response.buildV1(movies, "Ok");
    }
    
    @GetMapping(Routes.API_V1 + Routes.MOVIE+Routes.ID)
    public ResponseEntity<GlobalResponse> findById(@PathVariable("id") Integer idmovies) {
        Movie movie = useCase.findById(idmovies);
        return response.buildV1(movie, "Ok");
    }

    @PostMapping(Routes.API_V1 + Routes.MOVIE)
    public ResponseEntity<GlobalResponse> create(@Valid @RequestBody MovieCreateDto movieCreateDto) throws Exception {
        
        var movie=useCase.create(movieCreateDto);
        return response.buildV1(movie,"Success create Movie");
        
    }

    @PutMapping(Routes.API_V1 + Routes.MOVIE + Routes.ID)
    public ResponseEntity<GlobalResponse> update(@Valid @RequestBody MovieUpdateDto movieUpdateDto,@PathVariable("id") Integer idmovies) throws Exception {
        var movie=useCase.update(movieUpdateDto,idmovies);
        return response.buildV1(movie,"Success Update Movie");
    }

}
