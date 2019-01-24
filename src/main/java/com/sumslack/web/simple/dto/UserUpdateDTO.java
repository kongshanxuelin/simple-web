package com.sumslack.web.simple.dto;

public class UserUpdateDTO extends UserCreateDTO {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
