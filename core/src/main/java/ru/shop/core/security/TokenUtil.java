package ru.shop.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil {
    private static final String TOKEN_PREFIX = "Bearer";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public UserDetailsImpl parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            return new UserDetailsImpl(Integer.parseInt(body.getId()),
                    body.getSubject(),
                    (String) body.get("password"),
                    (String) body.get("roles"));

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(UserDetailsImpl u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.setId(u.getId().toString());
        claims.put("password", u.getPassword());
        claims.put("roles", u.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

}
