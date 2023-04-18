package com.example.flowers_marketplace.config;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/address/**").permitAll()
                .requestMatchers("/api/v1/balance/**").permitAll()
                .requestMatchers("/api/v1/card/**").permitAll()
                .requestMatchers("/api/v1/customer/**").permitAll()
                .requestMatchers("/api/v1/merchant/**").permitAll()
                .requestMatchers("/api/v1/store/**").permitAll()
                .requestMatchers("/api/v1/flower/**").permitAll()
                .anyRequest()
                .authenticated();

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
