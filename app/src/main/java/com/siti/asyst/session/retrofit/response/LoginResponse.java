package com.siti.asyst.session.retrofit.response;

import com.siti.asyst.session.model.User;

public class LoginResponse {
    //reques
    String status;
    String message;
    User data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
