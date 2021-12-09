package com.tenpo.api.dto;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    public final String userName;
    @NotNull
    public final String password;

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
