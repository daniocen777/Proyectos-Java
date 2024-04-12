package com.danicode.app.config;

import com.danicode.app.config.filter.JwtTokenValidator;
import com.danicode.app.service.UserDetailsServiceImpl;
import com.danicode.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    // Segun la imagen, primero configurar SecurityFilterChain
    //1° FORMA
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // Condiciones de seguridad (sin formularios, solo api)
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                // no guardar session en memoria (porque se usara tokens y su expiracion de este)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // autorizacion de url
                .authorizeHttpRequests(http -> {
                    // endpoints publicos
                    http.requestMatchers(
                                    new AntPathRequestMatcher("/auth/**", HttpMethod.POST.name()))
                            .permitAll();
                    // endpoints privados
                    http.requestMatchers(
                                    new AntPathRequestMatcher("/method/post", HttpMethod.POST.name()))
                            .hasAnyAuthority("CREATE", "READ");
                    http.requestMatchers(
                                    new AntPathRequestMatcher("/method/patch", HttpMethod.PATCH.name()))
                            .hasAuthority("REFACTOR");
                    http.requestMatchers(
                                    new AntPathRequestMatcher("/method/put", HttpMethod.PUT.name()))
                            .hasAnyRole("ADMIN", "DEVELOPER");
                    // endpoints NO ESPECIFICADOS
                    http.anyRequest().denyAll();
                })
                // Filtros propios => Validar token antes de BasicAuthenticationFilter
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }


    // 2° FORMA => usando anotaciones => Se hace directamente en el controlador

    /**
     * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
     * return httpSecurity
     * // Condiciones de seguridad (sin formularios, solo api)
     * .csrf(csrf -> csrf.disable())
     * .httpBasic(Customizer.withDefaults())
     * // no guardar session en memoria (porque se usara tokens y su expiracion de este)
     * .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     * // autorizacion de url
     * .build();
     * }
     */

    // SecurityFilterChain necesita de AuthenticationManager (administra la autenticacion)
    // AuthenticationManager usa el objeto de AuthenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // AuthenticationManager trabaja con proveedores de autenticacion
    // Se usara DaoAuthenticationProvider => que se conecta a la BD para traer el usaurio
    // Este Bean trabaja con PasswordEncoder (encriptar password) y UserDetailsService (se conecta a la BD)
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    // FUNCIONES UTILITARIAS

    // Spring security necesita de UserDetails para validarlos
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
