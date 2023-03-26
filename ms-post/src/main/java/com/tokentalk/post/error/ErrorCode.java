package com.tokentalk.post.error;

public enum ErrorCode {

    INVALID_AUTHOR_ID,
    MANDATORY_FIELD_NOT_DEFINED;

    public String code() {
        return name();
    }

}
