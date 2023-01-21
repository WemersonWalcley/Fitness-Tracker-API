package io.github.wemersonwalcley.fitness_tracker.security;

import io.github.wemersonwalcley.fitness_tracker.entity.CredentialEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtEncoder {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signature}")
    private String signatureKey;

    public String generateToken(CredentialEntity credentialEntity) {
        long expString = Long.parseLong(expiration);
        LocalDateTime dateHourExpiration = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dateHourExpiration.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return Jwts
                .builder()
                .setSubject(credentialEntity.getUsername())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, signatureKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime date = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return LocalDateTime.now().isAfter(date);
        } catch (Exception e) {
            return false;
        }
    }

    public Long getAccountId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.signatureKey).parseClaimsJws(token).getBody();
        String id = claims.get("id", String.class);
        return Long.parseLong(id);
    }

}
