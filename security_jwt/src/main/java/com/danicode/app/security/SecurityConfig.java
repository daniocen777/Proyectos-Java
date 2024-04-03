package com.danicode.app.security;

import com.danicode.app.security.filters.JwtAuthenticationFilter;
import com.danicode.app.security.filters.JwtAuthorizationFilter;
import com.danicode.app.security.jwt.JwtUtils;
import com.danicode.app.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// EnableGlobalMethodSecurity => Habilitar anotaciones en los controladores
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthorizationFilter authorizationFilter;

    /**
     * .csrf(config -> config.disable()) => sin formularios (solo API)
     * STATELESS => No crea ninguna sesion
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                            AuthenticationManager authenticationManager) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        // Por defecto, la URL para el logueo es "/login"
        // jwtAuthenticationFilter.setFilterProcessesUrl("/custom-login"); cambiar url por defecto

        return httpSecurity
                .csrf(config -> config.disable())
                // Proteccion de rutas
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(new AntPathRequestMatcher("/hello")).permitAll();
                    // auth.requestMatchers(new AntPathRequestMatcher("/access-admin")).hasAnyRole("ADMIN", "USER");
                    auth.anyRequest().authenticated();
                })
                // Sesiones
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                // Tipo de seguridad - FILTRO (basica con un usuario en memoria, o propia)
                // .httpBasic()
                // Filtros
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Usuario de prueba (en memoria)
     *
     * @Bean UserDetailsService userDetailsService() {
     * InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
     * manager.createUser(User.withUsername("danicode")
     * .password("123456")
     * .build());
     * <p>
     * return manager;
     * }
     */

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Administracion de usuarios (los usuario de prueba, encodificar password, etc)
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
