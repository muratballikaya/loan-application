package com.mb.auth.security;

import com.mb.auth.security.jwt.AuthEntryPointJwt;
import com.mb.auth.security.jwt.BaererTokenFilter;
import com.mb.auth.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

  @Value("${spring.h2.console.path}")
  private String h2ConsolePath;

  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  private static final String[] AUTH_WHITELIST = {
          "/api/v1/auth/**",
          "/v3/api-docs/**",
          "/v3/api-docs.yaml",
          "/swagger-ui/**",
          "/swagger-ui.html"
  };
  
  @Bean
  public BaererTokenFilter authenticationJwtTokenFilter() {
    return new BaererTokenFilter();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/api/auth/**").permitAll()
                          .requestMatchers("/api/test/**").permitAll()
                            .requestMatchers("/api/customer/**").permitAll()
                            .requestMatchers("/api/loan/**").permitAll()
                            .requestMatchers(AUTH_WHITELIST).permitAll()
                            .anyRequest().authenticated()
            );

    http.headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin()));
    http.authenticationProvider(authenticationProvider());

    return http.build();
  }
}
