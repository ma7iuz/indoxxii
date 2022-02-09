package com.indoxxii.indoxxii.applications.request.user;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UserSignUpDto {
    @NotBlank
    private String username;
    
    @NotBlank
    @Length(min = 10)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Date birthdate;

}
