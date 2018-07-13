package ru.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    private static final String SECRET = "Gk7kp1Hkd";
    private static final String TOKEN_PREFIX = "Bearer";

    public UserDetailsImpl parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
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
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getTokenPrefix() {
        return TOKEN_PREFIX;
    }

}
