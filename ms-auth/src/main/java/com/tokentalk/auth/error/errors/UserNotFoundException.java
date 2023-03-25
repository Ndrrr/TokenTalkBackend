package com.tokentalk.auth.error.errors;

import com.tokentalk.auth.error.BaseException;
import com.tokentalk.auth.error.ErrorCode;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(ErrorCode code, String message) {
        super(code, message);
    }

}
