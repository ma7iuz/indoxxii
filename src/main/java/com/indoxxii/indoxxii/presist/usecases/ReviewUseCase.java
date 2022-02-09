package com.indoxxii.indoxxii.presist.usecases;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import com.indoxxii.indoxxii.applications.request.review.ReviewCreateDto;
import com.indoxxii.indoxxii.applications.request.review.ReviewUpdateDto;
import com.indoxxii.indoxxii.presist.models.Review;
import com.indoxxii.indoxxii.presist.repositories.MovieRepository;
import com.indoxxii.indoxxii.presist.repositories.ReviewRepository;
import com.indoxxii.indoxxii.presist.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewUseCase {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;
    
    public Review findById(Integer idreview) {
        Optional<Review> optionalReview = reviewRepository.findById(idreview);
        Review review = optionalReview.get();

        return review;
    }

    public Set<Review> findAll() {
        List<Review> listReview = reviewRepository.findAll();

        return new HashSet<Review>(listReview);
    }

    public Review create(ReviewCreateDto dto) throws Exception {
        var newReview = new Review();
        var optionUser=userRepository.findById(dto.getIduser());
        if(!optionUser.isEmpty()){
            newReview.setUser(optionUser.get());
        }
        var optionMovie=movieRepository.findById(dto.getIdmovie());
        if(!optionMovie.isEmpty()){
            newReview.setMovie(optionMovie.get());
        }
        newReview.setStar(dto.getStar());
        newReview.setReview(dto.getReview());
        return reviewRepository.save(newReview);
    }

    public Review update(@Valid ReviewUpdateDto dto, Integer idreview)throws Exception {
        var optionReview=reviewRepository.findById(idreview);
        var review =optionReview.get();
        review.setStar(dto.getStar());
        review.setReview(dto.getReview());
        return reviewRepository.save(review);
    }

    public void delete( Integer idreview)throws Exception {
        var optionReview=reviewRepository.findById(idreview);
        var review =optionReview.get();
        reviewRepository.delete(review);
    }
}
