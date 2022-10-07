package com.example.demo.qerja.security;

import com.example.demo.qerja.exception.InvalidTokenException;
import com.example.demo.qerja.model.UserComputer;
import com.example.demo.qerja.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtToken {

    @Autowired
    private UserService userService;

    public String generateToken(UserComputer userComputer) {
        // Key is hardcoded here for simplicity.
        // Ideally this will get loaded from env configuration/secret vault
        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("username", userComputer.getUsername())
                .claim("password", userComputer.getPassword())
                .setSubject(userComputer.getUsername())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
                .signWith(hmacKey).compact();
        return jwtToken;
    }

    public Jws<Claims> parseJwt(String jwtString) {
        try{
            String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
            Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                    SignatureAlgorithm.HS256.getJcaName());
            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(jwtString);

            return jwt;
        }catch (Exception e){
            throw new InvalidTokenException();
        }
    }

    public boolean validate(String token) {
        Jws<Claims> claims = parseJwt(token);
        UserComputer userComputer = new UserComputer((String) claims.getBody().get("username"), "", (String) claims.getBody().get("password"));
        return userService.findUser(userComputer);
    }
}
