package com.example.flowers_marketplace.config;

import com.example.flowers_marketplace.security.JwtConfigure;
import com.example.flowers_marketplace.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .ignoringRequestMatchers("/api/v1/admins/create")
                .ignoringRequestMatchers("/api/v1/customers/create")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth").permitAll()
                .requestMatchers("/api/v1/customers/create").permitAll()
                .requestMatchers("/api/v1/merchants/**").hasAnyRole("ADMIN")
                .requestMatchers("/api/v1/stores/**").hasAnyRole("ADMIN")
                .requestMatchers("/api/v1/admins/**").hasAnyRole("ADMIN")
                .requestMatchers("/api/v1/carts/**").hasAnyRole("CUSTOMER")
                .requestMatchers("/api/v1/customers/**").hasAnyRole("CUSTOMER")
                .requestMatchers("/api/v1/merchants/**").hasAnyRole("MERCHANT")
                .requestMatchers("/api/v1/flowers/**").hasAnyRole("MERCHANT")
                .anyRequest()
                .authenticated()
                .and().apply(getJwtConfigure(jwtService));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    private JwtConfigure getJwtConfigure(JwtService jwtService) {
        return new JwtConfigure(jwtService);
    }


}
