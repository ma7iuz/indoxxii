package com.indoxxii.indoxxii.applications.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse extends GlobalResponse{
    private String token;
    private int id;
    private String username;

    public JwtResponse(String token,int id, String username){
        this.token=token;
        this.id=id;
        this.username=username;
    }
}
