package com.tenpo.dto;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    public final String userName;
    @NotNull
    public final String userNickName;
    @NotNull
    public final String password;

    public UserDTO(String userName, String userNickName, String password) {
        this.userName = userName;
        this.userNickName = userNickName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "userName='" + userName + '\'' +
            ", userNickName='" + userNickName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
