package com.tenpo.api.dto.response;

import javax.validation.constraints.NotNull;

public class UserDataResponse {

    @NotNull
    public final String userName;

    private UserDataResponse(String userName) {
        this.userName = userName;
    }

    public static UserDataResponse create(String userName) {
        return new UserDataResponse(userName);
    }

    @Override
    public String toString() {
        return "UserDataResponse{" +
            "userName='" + userName + '\'' +
            '}';
    }
}
