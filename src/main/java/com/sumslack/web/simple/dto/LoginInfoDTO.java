package com.sumslack.web.simple.dto;

import java.util.Set;

import com.sumslack.web.simple.model.UserModel;

public class LoginInfoDTO {
    // 用户信息
    private UserModel user;
    // 角色
    private Set<String> roles;
    // 所属Team
    private Set<String> belongTeamIds;
    // 负责的team
    private Set<String> ownerTeamIds;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getBelongTeamIds() {
        return belongTeamIds;
    }

    public void setBelongTeamIds(Set<String> belongTeamIds) {
        this.belongTeamIds = belongTeamIds;
    }

    public Set<String> getOwnerTeamIds() {
        return ownerTeamIds;
    }

    public void setOwnerTeamIds(Set<String> ownerTeamIds) {
        this.ownerTeamIds = ownerTeamIds;
    }

    @Override
    public String toString() {
        return "LoginInfoDTO{" +
                "user=" + user +
                ", roles=" + roles +
                ", belongTeamIds=" + belongTeamIds +
                ", ownerTeamIds=" + ownerTeamIds +
                '}';
    }
}
