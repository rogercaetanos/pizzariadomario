package com.itb.tcc.mif3an.pizzariadomario.security.config;

import com.itb.tcc.mif3an.pizzariadomario.security.exceptions.CustomAccessDeniedHandler;
import com.itb.tcc.mif3an.pizzariadomario.security.exceptions.CustomAuthenticationEntryPoint;
import com.itb.tcc.mif3an.pizzariadomario.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/index",
            "/images/**"
    };

 private final JwtAuthenticationFilter jwtAuthFilter;
 private final AuthenticationProvider authenticationProvider;
 private final LogoutHandler logoutHandler;
 private final CustomAuthenticationEntryPoint  customAuthenticationEntryPoint;
 private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthFilter,
            AuthenticationProvider authenticationProvider,
            LogoutHandler logoutHandler,
            CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
            CustomAccessDeniedHandler customAccessDeniedHandler) {

        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
        this.logoutHandler = logoutHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(AbstractHttpConfigurer::disable)
               .cors(cors -> cors.configurationSource(corsConfigurationSource()))
               .authorizeHttpRequests(req ->
                       req
                               .requestMatchers("/api/v1/auth/authenticate").permitAll()
                               .requestMatchers(HttpMethod.POST,"/api/v1/cliente/**").permitAll()

                               .requestMatchers(WHITE_LIST_URL).permitAll()
                               .anyRequest().authenticated()
                       )
               .exceptionHandling(exception -> exception
                       .authenticationEntryPoint(customAuthenticationEntryPoint)
                       .accessDeniedHandler(customAccessDeniedHandler)
               )
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authenticationProvider(authenticationProvider)
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .logout(logout ->
                       logout.logoutUrl("/api/v1/auth/logout")
                               .addLogoutHandler(logoutHandler)
                               .logoutSuccessHandler(
                                       (request, response, authentication) ->
                                               SecurityContextHolder.clearContext())
                     );

        return http.build();
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ORIGENS PERMITIDAS (ajuste conforme seu front-end)
        config.setAllowedOrigins(List.of(
                "http://localhost:8686", // seu front-end
                "http://localhost:5173",  // caso use Vite
                "http://localhost:5174"
        ));

        // MÉTODOS HTTP PERMITIDOS
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // CABEÇALHOS E CREDENCIAIS
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        // REGISTRA A CONFIGURAÇÃO PARA TODOS OS ENDPOINTS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
