package ru.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            return new User(body.getSubject(), (String) body.get("password"),  (Set<GrantedAuthority>) body.get("authorities"));

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    public String generateToken(UserDetails u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("password", u.getPassword());
        claims.put("authorities", u.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
