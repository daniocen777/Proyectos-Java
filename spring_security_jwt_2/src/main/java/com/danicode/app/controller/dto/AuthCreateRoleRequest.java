package com.danicode.app.controller.dto;

import com.danicode.app.persitence.enums.RoleEnum;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;
import java.util.List;

@Validated
public class AuthCreateRoleRequest {

    @Size(max = 3, message = "User cannot have more than 3 roles")
    private List<RoleEnum> rolesListName;

    public AuthCreateRoleRequest() {
    }

    public AuthCreateRoleRequest(List<RoleEnum> rolesListName) {
        this.rolesListName = rolesListName;
    }

    public List<RoleEnum> getRolesListName() {
        return rolesListName;
    }

    public void setRolesListName(List<RoleEnum> rolesListName) {
        this.rolesListName = rolesListName;
    }
}
