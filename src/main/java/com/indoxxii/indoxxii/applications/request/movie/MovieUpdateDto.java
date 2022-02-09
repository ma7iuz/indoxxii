package com.indoxxii.indoxxii.applications.request.movie;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.indoxxii.indoxxii.presist.models.MovieRating;

import lombok.Data;

@Data
public class MovieUpdateDto {
    @NotBlank
    private String title;

    @NotBlank
    private String synopsis;

    @NotBlank
    private String posterdirectory;

    @NotNull
    private MovieRating rating;

    @NotNull
    private Float price;
}
