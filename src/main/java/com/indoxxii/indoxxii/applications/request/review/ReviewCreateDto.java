package com.indoxxii.indoxxii.applications.request.review;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReviewCreateDto {
    @Min(value = 0L)
    @Max(value = 5)
    private Integer star;

    @NotBlank
    private String review;

    @NotNull
    private Integer idmovie;

    @NotNull
    private Integer iduser;
}
