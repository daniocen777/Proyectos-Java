package com.danicode.app.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey; // token se firma

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date((System.currentTimeMillis()))) // se emite a las
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Firma
    public Key getSignatureKey() {
        byte[] keyByte = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyByte);
    }

    // Obtener username del token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // Obtener un claim del token
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extarctAllClaims(token);

        return claimsTFunction.apply(claims);
    }

    // Obtener TODOS los claims del token (atributos que se envio al generar token)
    public Claims extarctAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Validar token de acceso
    public boolean isTokenValid(String token) {
        try {
            // Leer
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (Exception e) {
            System.out.println("Error isTokenValid => ".concat(e.getMessage()));
            return false;
        }
    }
}
