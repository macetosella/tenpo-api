package com.tenpo.dto.response;

import javax.validation.constraints.NotNull;

public class UserDataResponse {

    @NotNull
    public final String userName;
    @NotNull
    public final String userNickName;

    private UserDataResponse(String userName, String userNickName) {
        this.userName = userName;
        this.userNickName = userNickName;
    }

    public static UserDataResponse create(String userName, String userNickName) {
        return new UserDataResponse(userName, userNickName);
    }

    @Override
    public String toString() {
        return "UserDataResponse{" +
            "userName='" + userName + '\'' +
            ", userNickName='" + userNickName + '\'' +
            '}';
    }
}
