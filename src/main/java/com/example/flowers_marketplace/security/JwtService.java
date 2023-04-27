package com.example.flowers_marketplace.security;

import com.example.flowers_marketplace.model.Login;
import com.example.flowers_marketplace.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserDetailsService userDetailsService;

    @Value("${json.web.token.validation-time}")
    private Long validationTimeMillis;

    @Value("${json.web.token.refresh-time}")
    private Long refreshTimeMillis;

    @Value("${json.web.token.secret}")
    private String secret;

    public JwtService(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    private void init() {
        this.secret = Base64.getEncoder().encodeToString(this.secret.getBytes());

    }

    public String generateToken(Login login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Claims claims = Jwts.claims();
        claims.setSubject(login.getUsername());
        claims.put("roles", roles);
        Date issuedDate = new Date();
        Date expireDate = new Date(issuedDate.getTime() + validationTimeMillis);

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String refreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        Date now = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(now.getTime() + this.refreshTimeMillis);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .compact();

    }

    public Boolean shouldRefreshToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        Instant now = Instant.now();
        Instant expirationDate = claims.getExpiration().toInstant();
        Instant refreshTime = expirationDate.minusMillis(refreshTimeMillis);

        return now.isAfter(refreshTime); //20.04.2023 21.04.2023
    }

    public Boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            Date now = new Date();
            return !claims.getExpiration().before(now);  //20.04.2023 21.04.2023
        } catch (MalformedJwtException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Authentication getAuthentication(String token) {
        if (isValid(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(extractUsername(token));

            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        }


        return null;
    }
}
