package com.huan.identity_service.configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final String[] PUBLIC_ENDPOINTS = {"/users", "/auth/token", "/auth/introspect"};

  @Value("${jwt.signerKey}")
  String signerKey;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        request ->
            request
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                .permitAll()
                .anyRequest()
                .authenticated());
    http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

    // sign authentication provider
    http.oauth2ResourceServer(
        oath2 -> oath2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

    return http.build();
  }

  @Bean
  JwtDecoder jwtDecoder() {
    SecretKey secretKey = new SecretKeySpec(signerKey.getBytes(), "HS512");
    return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS512).build();
  }
}
