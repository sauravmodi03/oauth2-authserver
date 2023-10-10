package com.oauth.oauthserver.serverconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Component
public class CorsConfig {

    public void corsConfigurationSource(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors( c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration config = new CorsConfiguration();
                config.addAllowedHeader("*");
                config.addAllowedMethod("*");
                config.addAllowedOrigin("http://localhost:3000");
                config.setAllowCredentials(true);
                return config;
            };
            c.configurationSource(source);
        });
    }
}
