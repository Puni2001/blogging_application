package com.blog.blogging_application.config;

import com.blog.blogging_application.security.CustomUserDetailService;
import com.blog.blogging_application.security.JwtAuthenticationEntryPoint;
import com.blog.blogging_application.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {
        "/api/v1/auth/**",
        "/v3/api-docs/**", "/v2/api-docs/**",
        "/swagger-resources/**", "/swagger-ui/**",
        "/webjars/**"
    };

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // 1) PasswordEncoder bean for @Autowired injections
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2) DaoAuthenticationProvider wired with your UserDetailsService + encoder
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // 3) Expose the AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 4) Define the main security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // disable CSRF for stateless REST APIs
            .csrf(csrf -> csrf.disable())

            // enable CORS with our CorsConfigurationSource bean
            .cors().and()

            // no sessions—REST is stateless
            .sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // handle auth errors via our entry point
            .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))

            // URL authorization
            .authorizeHttpRequests(auth -> auth
                // public endpoints
                .requestMatchers(PUBLIC_URLS).permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                // all others need authentication
                .anyRequest().authenticated()
            )

            // register our DAO auth provider
            .authenticationProvider(daoAuthenticationProvider())

            // JWT filter before username/password auth filter
            .addFilterBefore(jwtAuthenticationFilter, 
                             org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 5) CORS config
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowCredentials(true);
        cfg.setAllowedOriginPatterns(List.of("*"));
        cfg.setAllowedHeaders(List.of("Authorization","Content-Type","Accept"));
        cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        cfg.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return src;
    }
}
