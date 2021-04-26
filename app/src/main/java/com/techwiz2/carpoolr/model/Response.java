package com.techwiz2.carpoolr.model;

public class Response {
    private boolean status;
    private Object data;

    public Response(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
