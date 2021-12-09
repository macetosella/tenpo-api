package com.tenpo.api.model;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long userId;
    @Column(unique = true)
    public String userName;
    public String password;
    public Date created;

    public UserData() {

    }

    public UserData(String userName, String password, Date created) {
        this.userName = userName;
        this.password = hashPassword(password);
        this.created = created;
    }

    public static String hashPassword(String password) {
        return Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString();
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", created=" + created +
            '}';
    }
}
