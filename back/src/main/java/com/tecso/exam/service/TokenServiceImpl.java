package com.tecso.exam.service;

import com.tecso.exam.domain.User;
import com.tecso.exam.domain.dto.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String JWT_SECRET = "UnsecureTokenTecso1231254123asdfaasfasdf";

    @Override
    public String generateToken(User user) {
        Instant expirationTime = Instant.now().plus(365, ChronoUnit.DAYS);
        Date expirationDate = Date.from(expirationTime);

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

        String compactTokenString = Jwts.builder()
                .claim("id", user.getId())
                .claim("sub", user.getUsername())
                .claim("admin", user.isAdmin())
                .claim("name", user.getName())
                .claim("lastname", user.getLastname())
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return "Bearer " + compactTokenString;
    }

    @Override
    public UserDTO parseToken(String token) {
        byte[] secretBytes = JWT_SECRET.getBytes();

        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(secretBytes)
                .build()
                .parseClaimsJws(token);

        Claims body = jwsClaims.getBody();

        String username = body.getSubject();
        String userId = body.get("id", String.class);
        Boolean isAdmin = body.get("admin", Boolean.class);
        String name = body.get("name", String.class);
        String lastname = body.get("lastname", String.class);

        return new UserDTO(userId, name, username, lastname, isAdmin);
    }
}
