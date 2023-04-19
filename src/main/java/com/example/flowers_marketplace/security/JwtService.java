package com.example.flowers_marketplace.security;

import com.example.flowers_marketplace.model.Login;
import com.example.flowers_marketplace.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserDetailsService userDetailsService;

    @Value("${json.web.token.validity}")
    private Long validityTime;

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
        Date expireDate = new Date(issuedDate.getTime() + validityTime);

        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean isValid(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            Date now = new Date();
            return !claims.getExpiration().before(now);
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
