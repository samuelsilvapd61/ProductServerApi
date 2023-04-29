package com.samuel.product.server.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.samuel.product.server.api.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    /**
     * Gera o token de auteticação quando o usuário faz o login
     * @param usuario User
     * @return String
     */
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

    /**
     * Verifica se o token ainda é válido ou se expirou
     * @param token Token usado para fazer acesso quando usuário já está logado
     * @return String
     */
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("senha"))
                .withIssuer("Produtos")
                .build().verify(token).getSubject();
    }
}
