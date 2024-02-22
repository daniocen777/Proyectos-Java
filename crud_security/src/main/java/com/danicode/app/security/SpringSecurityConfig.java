package com.danicode.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // csrf => token en formularios en el front, pero esta app solo es apiRest
        // Permitir crud a usuarios, lo demas necesita autenticacion
        return httpSecurity.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(new AntPathRequestMatcher("/api/users"))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf(config -> config.disable())
                .sessionManagement(maagement -> maagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
