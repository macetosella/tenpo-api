package com.tenpo.dto;

import java.util.Date;

public class UserDTO {

    public final String userName;
    public final String userNickName;
    public final String password;

    public UserDTO(String userName, String userNickName, String password) {
        this.userName = userName;
        this.userNickName = userNickName;
        this.password = password;
    }
}
