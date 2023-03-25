package com.tokentalk.auth.error;

public enum ErrorCode {

    USER_NOT_FOUND,
    USER_ALREADY_EXISTS,
    INVALID_JWT,
    MANDATORY_FIELD_NOT_DEFINED;

    public String code() {
        return name();
    }

}
