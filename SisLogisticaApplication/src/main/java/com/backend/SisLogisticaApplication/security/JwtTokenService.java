package com.backend.SisLogisticaApplication.security;

import com.backend.SisLogisticaApplication.model.UserAccount;
import com.backend.SisLogisticaApplication.repository.UserAccountRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtTokenService {

    @Value("${app.jwt.secret}")
    private String secret; // deve ter 32+ caracteres

    @Value("${app.jwt.expiration}")
    private long expiration; // em ms

    private SecretKey key;

    @Autowired
    private UserAccountRepository userRepository;

    @PostConstruct
    public void init() {

        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String gerarToken(String username) {
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + expiration);
        UserAccount usuario = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return Jwts.builder()
                .setSubject(username)
                .claim("role", usuario.getRole())
                .setIssuedAt(agora)
                .setExpiration(validade)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameDoToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}