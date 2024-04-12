package com.danicode.app.service;

import com.danicode.app.controller.dto.AuthCreateUserRequest;
import com.danicode.app.controller.dto.AuthLoginRequest;
import com.danicode.app.controller.dto.AuthResponse;
import com.danicode.app.persitence.entity.RoleEntity;
import com.danicode.app.persitence.entity.UserEntity;
import com.danicode.app.persitence.enums.RoleEnum;
import com.danicode.app.persitence.repository.IRoleRepository;
import com.danicode.app.persitence.repository.IUserRepository;
import com.danicode.app.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no existe"));
        // Roles => Spring security lo maneja con authorities
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        // Obteniendo los roles de UserEntity
        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        // Permisos
        // flatMap => volver a recorrer
        userEntity.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse login(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.getUsername();
        String password = authLoginRequest.getPassword();
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "User logged successfully", accessToken, true);
        return authResponse;
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
        String username = authCreateUserRequest.getUsername();
        String password = authCreateUserRequest.getPassword();
        List<RoleEnum> roles = authCreateUserRequest.getAuthCreateRoleRequest().getRolesListName();
        Set<RoleEntity> roleEntitySet = roleRepository.findRoleEntityByRoleEnumIn(roles)
                .stream().collect(Collectors.toSet());
        if (roleEntitySet.isEmpty()) {
            throw new IllegalArgumentException("Roles specified not exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setRoles(roleEntitySet); // cada rol tiene sus permisos
        userEntity.setEnabled(true);
        userEntity.setAccountNoLocked(true);
        userEntity.setAccountNoExpired(true);
        userEntity.setCredentialNoExpired(true);

        UserEntity userCreated = userRepository.save(userEntity);
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        // Seteando roles
        userCreated.getRoles().forEach(role ->
                authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        // Seteando permisos
        userCreated.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions()
                        .stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        // SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userCreated.getUsername(),
                userCreated.getPassword(),
                authorityList);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(
                userCreated.getUsername(),
                "User created successfully",
                accessToken,
                true);

        return authResponse;
    }
}
