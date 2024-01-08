package com.api.neki.security;

import com.api.neki.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JWTService {

    private static final String SECURITY_KEY = "ChaveSecretaDaNeki";

    public String gerarToken(Authentication authentication) {

        int tempoExpiracao = 86400000;
        Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
        User usuario = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
    }

    public Optional<Long> obterIdDoUsuario(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();
            return Optional.ofNullable(Long.parseLong(claims.getSubject()));

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
