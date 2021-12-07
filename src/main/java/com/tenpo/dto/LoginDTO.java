package com.tenpo.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull
    public final String userName;
    @NotNull
    public final String password;

    public LoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
            "userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
