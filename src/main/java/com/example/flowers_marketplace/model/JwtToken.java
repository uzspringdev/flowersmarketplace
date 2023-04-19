package com.example.flowers_marketplace.model;

public class JwtToken {
    private String tokenId;

    public JwtToken(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
