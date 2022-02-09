package com.indoxxii.indoxxii.applications.security.jwt;

import java.security.Key;
import java.util.Date;

import com.indoxxii.indoxxii.applications.security.user.UserDetailsImplement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;


@Component
public class JwtUtils {
    @Value("${indoxxii.jwt.secret}")
    private String secret;

    @Value("${indoxxii.jwt.tokenMinutesExpired}")
    private Integer minutesExpired;
    
    public String generateJwtToken(Authentication authentication) {
        UserDetailsImplement userPrincipal = (UserDetailsImplement) authentication.getPrincipal();
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        Key key=Keys.hmacShaKeyFor(keyBytes);
        return Jwts.builder()
        .setSubject(userPrincipal.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + (minutesExpired*60*1000)))
        .signWith(key)
        .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret)
        .build()
        .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(this.secret);
            Jwts.parserBuilder()
            .setSigningKey(keyBytes)
            .build()
            .parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.err.println("Fail validate jwt"+e.getMessage());
        }
        return false;
    }
}