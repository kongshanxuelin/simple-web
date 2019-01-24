package com.sumslack.web.simple.dto;

public class UserWithRoleDTO extends UserDTO{

    private String roleType;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
