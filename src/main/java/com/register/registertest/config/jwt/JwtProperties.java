package com.register.registertest.config.jwt;

public interface JwtProperties {

    String SECRET = "luckywater";
    int EXPIRATION_TIME = 60000*60;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}