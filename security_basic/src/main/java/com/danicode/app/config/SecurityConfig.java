package com.danicode.app.config;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* 1° Forma
    csrf => Cross-Site Request Forgery
        .csrf().disable => Para APIs
        usarlo cuando se usa formularios

    .authorizeHttpRequests() => Url protegidas (todas protegidas por defecto)
    .requestMatchers("") => Permitir consulta (no necesitan autorizacion)
    .formLogin().permitAll() => acceder al formulario (por defecto de spring security)

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/v1/index2")).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .build();
    } */

    /* 2° Forma => Usando lambda
    - successHandler() => Cuando login sea correcto, redirigir a url
    - .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
          -- ALWAYS => se crea session si no existe (reutiliza si ya existe)
          -- IF_REQUIRED => Crea una nueva sesion si es necesario (mas extricto que ALWAYS - evalua si es necesario crear session)
          -- NEVER => No crea ninguna sesion (si existe una, la utiliza)
          -- STATELESS => No crea ninguna sesion
          .sessionRegistry() => Administra los registros de la sesion
     - .invalidSessionUrl("URL") => Si la sesion es invalida, se redirige a la url asignada (URL)
     - .expiredUrl("/login") => Expiracion de sesion
     - .sessionFixation() => Evitar vulneraciones con el ID de sesion
        -- .migrateSession() => Spring genera otro ID de sesion (copia los datos del usuario)
        -- .newSession() => genera otro ID (no copia los datos), crear una sesion nueva
        -- .none() => No hace nada
     * */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Autorizaciones
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(new AntPathRequestMatcher("/v1/index2")).permitAll();
                    auth.anyRequest().authenticated();
                })
                // Login
                .formLogin()
                .successHandler(successHandler())
                .and()
                // Sesiones
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")
                .sessionRegistry(sessionRegistry())
                .and()
                // Vulnerabilidades
                .sessionFixation()
                .migrateSession()
                .and()
                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    private AuthenticationSuccessHandler successHandler() {
        return (((request, response, authentication) -> {
            // response.sendRedirect("/v1/index");
            response.sendRedirect("/v1/session");
        }));
    }
}
