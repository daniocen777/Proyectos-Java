package com.danicode.blog.security.controller;

import com.danicode.blog.entity.Message;
import com.danicode.blog.security.dto.JwtDTO;
import com.danicode.blog.security.dto.LoginUserDTO;
import com.danicode.blog.security.dto.UserDTO;
import com.danicode.blog.security.entity.Role;
import com.danicode.blog.security.entity.User;
import com.danicode.blog.security.enums.RoleList;
import com.danicode.blog.security.jwt.JwtProvider;
import com.danicode.blog.security.service.IRoleService;
import com.danicode.blog.security.service.IUserService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginUserDTO loginUser, BindingResult bidBindingResult) {
        if (bidBindingResult.hasErrors()) {
            System.out.println(bidBindingResult.getAllErrors());
            return new ResponseEntity<>(new Message("Revise sus credenciales 1"), HttpStatus.BAD_REQUEST);
        }
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            JwtDTO jwtDto = new JwtDTO(jwt);
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> resgister(@Valid @RequestBody UserDTO newUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new Message("Revise los campos e intente nuevamente"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getUsername(), newUser.getEmail(),
                passwordEncoder.encode(newUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getByRoleName(RoleList.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            roles.add(roleService.getByRoleName(RoleList.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new Message("Registro exitoso! Inicie sesión"), HttpStatus.CREATED);
    }
}
