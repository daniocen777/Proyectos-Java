package com.danicode.marvel.service;

import com.danicode.marvel.dto.security.LoginRequest;
import com.danicode.marvel.dto.security.LoginResponse;
import com.danicode.marvel.persistence.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final HttpSecurity httpSecurity;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(HttpSecurity httpSecurity,
                                 UserDetailsService userDetailsService,
                                 AuthenticationManager authenticationManager) {
        this.httpSecurity = httpSecurity;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        // Cargar al usuario desde la BD
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        // autenticar
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, loginRequest.getPassword(), userDetails.getAuthorities()
        );
        authenticationManager.authenticate(authentication);
        // token
        // String jwt = jwtService.generateToken(userDetails, generateExtraClaims(userDetails));

        return new LoginResponse("jwt");
    }

    public void logout() {
        try {
            httpSecurity.logout(
                    config -> config.deleteCookies("JSESSIONID")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true));
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private Map<String, Object> generateExtraClaims(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        String roleName = ((User) userDetails).getRole().getName().name();
        extraClaims.put("role", roleName);
        extraClaims.put("authorities", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return extraClaims;
    }
}
