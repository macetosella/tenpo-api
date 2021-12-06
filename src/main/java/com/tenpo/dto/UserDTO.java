package com.tenpo.dto;

import java.util.Date;

public class UserDTO {

    public final String userName;
    public final String userNickName;
    public final String password;
    public final Date created;

    public UserDTO(String userName, String userNickName, String password, Date created) {
        this.userName = userName;
        this.userNickName = userNickName;
        this.password = password;
        this.created = created;
    }
}
