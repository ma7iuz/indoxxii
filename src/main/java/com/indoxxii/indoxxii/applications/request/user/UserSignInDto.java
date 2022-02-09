package com.indoxxii.indoxxii.applications.request.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserSignInDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
