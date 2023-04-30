package com.samuel.product.server.api.service.impl;

import com.samuel.product.server.api.domain.User;
import com.samuel.product.server.api.domain.request.UserRequest;
import com.samuel.product.server.api.domain.response.TokenResponse;
import com.samuel.product.server.api.exception.ApiError;
import com.samuel.product.server.api.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public TokenResponse doLogin(UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            Authentication authenticate =
                    authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            var usuario = (User) authenticate.getPrincipal();

            var tokenResponse = TokenResponse.builder()
                    .token(tokenService.gerarToken(usuario))
                    .build();
            return tokenResponse;
        } catch (Exception e) {
            throw new ApiException(ApiError.INCORRECT_USER_OR_PASSWORD);
        }
    }

}
