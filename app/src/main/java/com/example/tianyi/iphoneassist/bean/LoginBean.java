package com.example.tianyi.iphoneassist.bean;

import java.io.Serializable;

/**
 * Created by Tianyi on 2017/11/20.
 */

public class LoginBean implements Serializable {

    private String token;
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
