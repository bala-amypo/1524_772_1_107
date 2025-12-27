package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth

                /* ---------- AUTH (PUBLIC) ---------- */
                .requestMatchers("/auth/**").permitAll()

                /* ---------- SWAGGER ---------- */
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-ui/index.html",
                        "/v3/api-docs/**"
                ).permitAll()

                /* ---------- PARCEL ---------- */
                .requestMatchers(HttpMethod.POST, "/api/parcels/**")
                    .hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/parcels/**")
                    .hasAnyRole("ADMIN", "AGENT")

                /* ---------- DAMAGE CLAIM ---------- */
                .requestMatchers(HttpMethod.POST, "/api/claims/file/**")
                    .hasRole("AGENT")

                .requestMatchers(HttpMethod.PUT, "/api/claims/evaluate/**")
                    .hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/claims/**")
                    .hasAnyRole("ADMIN", "AGENT")

                /* ---------- EVIDENCE ---------- */
                .requestMatchers("/api/evidence/**")
                    .hasAnyRole("ADMIN", "AGENT")

                /* ---------- CLAIM RULES ---------- */
                .requestMatchers("/api/rules/**")
                    .hasRole("ADMIN")

                /* ---------- EVERYTHING ELSE ---------- */
                .anyRequest().authenticated()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
