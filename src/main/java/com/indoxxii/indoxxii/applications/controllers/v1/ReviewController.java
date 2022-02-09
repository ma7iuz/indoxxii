package com.indoxxii.indoxxii.applications.controllers.v1;

import java.util.Set;

import javax.validation.Valid;

import com.indoxxii.indoxxii.applications.request.review.ReviewCreateDto;
import com.indoxxii.indoxxii.applications.request.review.ReviewUpdateDto;
import com.indoxxii.indoxxii.applications.response.GlobalResponse;
import com.indoxxii.indoxxii.applications.response.Response;
import com.indoxxii.indoxxii.global.Routes;
import com.indoxxii.indoxxii.presist.models.Review;
import com.indoxxii.indoxxii.presist.usecases.ReviewUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    private ReviewUseCase useCase;

    private Response response = new Response();

    @GetMapping(Routes.API_V1 + Routes.REVIEW)
    public ResponseEntity<GlobalResponse> index(){
        Set<Review>reviews=useCase.findAll();
        return response.buildV1(reviews, "Ok");
    }
    @GetMapping(Routes.API_V1+Routes.REVIEW+Routes.ID)
    public ResponseEntity<GlobalResponse> findById(@PathVariable("id") Integer idreview) {
        Review review = useCase.findById(idreview);
        return response.buildV1(review, "Ok");
    }

    @PostMapping(Routes.API_V1 + Routes.REVIEW)
    public ResponseEntity<GlobalResponse> create(@Valid @RequestBody ReviewCreateDto reviewCreateDto) throws Exception {
        var review=useCase.create(reviewCreateDto);
        return response.buildV1(review,"Success create Review");
    }

    @PutMapping(Routes.API_V1 + Routes.REVIEW + Routes.ID)
    public ResponseEntity<GlobalResponse> update(@Valid @RequestBody ReviewUpdateDto reviewUpdateDto,
            @PathVariable("id") Integer idreview) throws Exception {
        var review=useCase.update(reviewUpdateDto, idreview);
        return response.buildV1(review, "Success update Review");
    }

    @DeleteMapping(Routes.API_V1+Routes.REVIEW+Routes.ID)
    public ResponseEntity<GlobalResponse> delete(@PathVariable("id") Integer idreview) throws Exception{
        useCase.delete(idreview);
        return response.buildV1("Success delete Review " + idreview, "200");
    }
}
