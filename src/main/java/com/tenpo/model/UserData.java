package com.tenpo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long userId;
    public String userName;
    public String userNickName;
    public String password;
    public Date created;

    public UserData() {

    }

    public UserData(String userName, String userNickName, String password, Date created) {
        this.userName = userName;
        this.userNickName = userNickName;
        this.password = password;
        this.created = created;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", userNickName='" + userNickName + '\'' +
            ", password='" + password + '\'' +
            ", created=" + created +
            '}';
    }
}
