package org.beefleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthConverter jwtAuthConverter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                                .jwt(jwt -> jwt
                                        .jwtAuthenticationConverter(jwtAuthConverter))
//                        .jwt(Customizer.withDefaults())
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();

    }
}
