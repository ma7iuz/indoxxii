package com.indoxxii.indoxxii.applications.request.review;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReviewUpdateDto {
    @Min(value = 0L)
    @Max(value = 5)
    private Integer star;

    @NotBlank
    private String review;
}
