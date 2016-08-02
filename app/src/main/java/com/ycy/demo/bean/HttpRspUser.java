package com.ycy.demo.bean;

import java.io.Serializable;

/**
 * Created by YCY.
 */
public class HttpRspUser implements Serializable {
    private String status;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
