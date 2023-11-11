package com.example.fetelix.configuration.security;

import com.example.fetelix.constants.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/account/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/account/google").permitAll()
                        .requestMatchers("/uploading/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/rest-api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/api/categories/**").hasAuthority(Roles.Admin)
                        .requestMatchers(HttpMethod.GET, "/api/genre").permitAll()
                        .requestMatchers("/api/genre/**").hasAuthority(Roles.Admin)
                        .requestMatchers(HttpMethod.GET, "/api/actor").permitAll()
                        .requestMatchers("/api/actor/**").hasAuthority(Roles.Admin)
                        .requestMatchers(HttpMethod.GET, "/api/director").permitAll()
                        .requestMatchers("/api/director/**").hasAuthority(Roles.Admin)
                        .requestMatchers(HttpMethod.GET, "/api/movie").permitAll()
                        .requestMatchers("/api/movie/**").hasAuthority(Roles.Admin)
                        .anyRequest().authenticated()
                )
                .sessionManagement(it->it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}