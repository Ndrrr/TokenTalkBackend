package com.example.apigateway.error;

public enum ErrorCode {

    MISSING_AUTHORIZATION_HEADER,
    INVALID_SUBJECT,
    INVALID_TOKEN;

    private final int status;

    ErrorCode() {
        this.status = 400;
    }

    ErrorCode(int status) {
        this.status = status;
    }

    public String code() {
        return name();
    }

    public int status() {
        return status;
    }

}
