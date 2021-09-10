package com.danicode.market.web.security;

import com.danicode.market.domain.service.DanicodeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Usar las credenciales creadas en la clase DanicodeUserDetailsService
    // Sobreescribir configure(AuthenticationManagerBuilder auth)
    @Autowired
    private DanicodeUserDetailsService danicodeUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(danicodeUserDetailsService);
    }
}
