package com.siti.asyst.session.retrofit.response;

import com.siti.asyst.session.model.Task;

public class TaskResponse {
    String status;
    String message;
    Task data;

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

    public Task getData() {
        return data;
    }

    public void setData(Task data) {
        this.data = data;
    }
}
