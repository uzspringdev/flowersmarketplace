package com.example.flowers_marketplace.controller;

import com.example.flowers_marketplace.model.Login;
import com.example.flowers_marketplace.security.JWTProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class HomeController {
    private final JWTProvider jwtProvider;

    public HomeController(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping(value = "/signIn")
    public ResponseEntity<?> signIn(@RequestBody Login login) {
        String token = jwtProvider.createToken(login);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return new ResponseEntity<>(new JwtToken(token), headers, HttpStatus.OK);
    }

    static class JwtToken {
        private String tokenId;

        JwtToken(String tokenId) {
            this.tokenId = tokenId;
        }

        public String getTokenId() {
            return tokenId;
        }

        public void setTokenId(String tokenId) {
            this.tokenId = tokenId;
        }
    }

}
