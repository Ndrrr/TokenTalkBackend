package com.tokentalk.comment.error;

public enum ErrorCode {

    INVALID_AUTHOR_EMAIL,
    POST_NOT_FOUND,
    MANDATORY_FIELD_NOT_DEFINED;

    public String code() {
        return name();
    }

}
