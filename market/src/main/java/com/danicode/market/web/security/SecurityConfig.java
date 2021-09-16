package com.danicode.market.web.security;

import com.danicode.market.domain.service.DanicodeUserDetailsService;
import com.danicode.market.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Usar las credenciales creadas en la clase DanicodeUserDetailsService
    // Sobreescribir configure(AuthenticationManagerBuilder auth)
    @Autowired
    private DanicodeUserDetailsService danicodeUserDetailsService;

    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(danicodeUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // PERMITIR Todas las peticiones que terminen en authenticate
        // Las otras peticiones, necesitan autenticarse => anyRequest().authenticated()
        http.csrf().disable().authorizeRequests()
                .antMatchers("/**/authenticate").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(this.jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    // Incluir AuthenticationManager con anotación @Bean para inyectarlo y usarlo
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
