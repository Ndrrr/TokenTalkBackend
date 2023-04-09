package com.tokentalk.post.error;

public enum ErrorCode {

    INVALID_AUTHOR_EMAIL,
    POST_NOT_FOUND,
    POST_FILE_NOT_FOUND,
    INVALID_FILE_EXTENSION,
    FILE_READ_ERROR,
    INVALID_FILE_TYPE,
    MANDATORY_FIELD_NOT_DEFINED;

    public String code() {
        return name();
    }

}
