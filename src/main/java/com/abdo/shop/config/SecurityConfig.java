package com.abdo.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.abdo.shop.model.entity.Role;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    final private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/api/v1/auth/login").permitAll();
                    request.requestMatchers("/api/v1/auth/**").hasAuthority(Role.ADMIN.toString());
                    request.requestMatchers(HttpMethod.POST, "/api/v1/items", "/api/v1/items/*/photo")
                            .hasAnyAuthority(Role.ADMIN.toString());
                    request.requestMatchers(HttpMethod.DELETE, "/api/v1/items", "/api/v1/items/photo/*",
                            "/api/v1/receipt/*").hasAnyAuthority(Role.ADMIN.toString());
                    request.requestMatchers(HttpMethod.PATCH, "/api/v1/items").hasAnyAuthority(Role.ADMIN.toString());
                    request.requestMatchers(HttpMethod.PUT, "/api/v1/items").hasAnyAuthority(Role.ADMIN.toString());
                    request.requestMatchers("/api/v1/worker/**").hasAuthority(Role.ADMIN.toString());
                    // request.requestMatchers("").hasAnyAuthority(Role.ADMIN.toString());
                    request.anyRequest().hasAnyAuthority(Role.ADMIN.toString(),
                            Role.WORKER.toString());
                })
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
}
