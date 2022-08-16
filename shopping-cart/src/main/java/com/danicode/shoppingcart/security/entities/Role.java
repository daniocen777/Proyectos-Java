package com.danicode.shoppingcart.security.entities;

import com.danicode.shoppingcart.security.enums.RoleList;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleList roleName;

    public Role() {
    }

    public Role(RoleList roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleList getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleList roleName) {
        this.roleName = roleName;
    }
}
