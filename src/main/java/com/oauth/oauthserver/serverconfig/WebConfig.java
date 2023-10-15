package com.oauth.oauthserver.serverconfig;

import com.oauth.oauthserver.services.JPAUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebConfig {

    private final CorsConfig corsConfig;

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( a -> {
            a.requestMatchers("/user/test/**").permitAll();
            a.requestMatchers("/user/register/**").permitAll();
            a.anyRequest().authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        corsConfig.corsConfigurationSource(http);
        http.authenticationProvider(authenticationProvider);
        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new JPAUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
