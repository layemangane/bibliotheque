package org.library.bibliotheque.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {

    // En production, cette clé viendrait d'une variable d'environnement, jamais codée en dur !
    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "cette-cle-doit-faire-au-moins-256-bits-pour-etre-valide-avec-hs256".getBytes()
    );

    private final long dureeValiditeMs = 1000 * 60 * 60; // 1 heure

    public String genererToken(String email, String role) {
        return Jwts.builder()
                .subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + dureeValiditeMs))
                .signWith(secretKey)
                .compact();
    }

    public String extraireEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .getSubject();
    }

    public String extraireRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public boolean estValide(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
