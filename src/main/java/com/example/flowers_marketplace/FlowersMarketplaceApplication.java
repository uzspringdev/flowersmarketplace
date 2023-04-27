package com.example.flowers_marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Date;

@SpringBootApplication
public class FlowersMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowersMarketplaceApplication.class, args);
    }
}
