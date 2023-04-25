package com.example.flowers_marketplace.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

public class JwtConfigure extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtService jwtService;

    public JwtConfigure(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtSecurityFilter jwtSecurityFilter = new JwtSecurityFilter(jwtService);
        CsrfHeaderFilter csrfHeaderFilter = new CsrfHeaderFilter();
        http
                .headers().frameOptions().disable()
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(csrfHeaderFilter, CsrfFilter.class);
    }
}
