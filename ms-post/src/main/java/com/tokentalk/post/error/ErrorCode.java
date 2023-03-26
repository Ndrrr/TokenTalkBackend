package com.tokentalk.post.error;

public enum ErrorCode {

    INVALID_AUTHOR_ID,
    POST_NOT_FOUND,
    POST_FILE_NOT_FOUND,
    MANDATORY_FIELD_NOT_DEFINED;

    public String code() {
        return name();
    }

}
