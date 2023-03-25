package com.tokentalk.auth.error;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final String code;

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ErrorCode code, String message) {
        this(code.code(), message);
    }

    public static BaseException of(ErrorCode code, String message) {
        return new BaseException(code, message);
    }

}
