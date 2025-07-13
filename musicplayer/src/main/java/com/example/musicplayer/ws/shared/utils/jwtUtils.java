package com.example.musicplayer.ws.shared.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class jwtUtils {
    @Value("${app.jwt.secret}")
    private  String jwtSecret; 
    private final long jwtExpirationMs = 86400000;

    public String generateJWToken(String subject){
        return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
    }

    // retrieve user's email from a given jwt
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //validate a given jwt
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
