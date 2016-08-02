package com.ycy.demo.bean;

import java.io.Serializable;

/**
 * Created by YCY.
 */
public class HttpReqUser implements Serializable {
    private String userName;
    private String password;

    public HttpReqUser() {
    }

    public HttpReqUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
