package com.example.flowers_marketplace.security;

import com.example.flowers_marketplace.model.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTProvider {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Value("${json.web.token.validity}")
    private Long validityTime;

    @Value("${json.web.token.secret}")
    private String secret;

    private byte[] secretKey;

    public JWTProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostConstruct
    private void init() {
        this.secretKey = Base64.getEncoder().encode(this.secret.getBytes());

    }

    public String createToken(Login login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Claims claims = Jwts.claims();
        claims.setSubject(login.getUsername());
        claims.put("roles", roles);
        Date issuedDate = new Date();
        Date expireDate = new Date(issuedDate.getTime() + validityTime);
        SecretKey secretKey = new SecretKeySpec(this.secretKey, SignatureAlgorithm.HS256.getJcaName());

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expireDate)
                .signWith(secretKey)
                .compact();
    }
}
