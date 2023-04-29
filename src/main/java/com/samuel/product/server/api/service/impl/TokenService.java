package com.samuel.product.server.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.samuel.product.server.api.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String gerarToken(User usuario) {
        return JWT.create()
                .withIssuer("Produtos")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(
                        LocalDateTime.now()
                        .plusSeconds(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256("senha"));

    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("senha"))
                .withIssuer("Produtos")
                .build().verify(token).getSubject();
    }
}
