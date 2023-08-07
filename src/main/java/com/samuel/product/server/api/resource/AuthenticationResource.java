package com.samuel.product.server.api.resource;

import com.samuel.product.server.api.domain.request.UserRequest;
import com.samuel.product.server.api.domain.response.TokenResponse;
import com.samuel.product.server.api.service.impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationResource {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody UserRequest request) {
        var tokenResponse = userService.doLogin(request);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
    }

}
