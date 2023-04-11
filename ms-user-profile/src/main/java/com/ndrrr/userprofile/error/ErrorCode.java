package com.ndrrr.userprofile.error;

public enum ErrorCode {

    PROFILE_NOT_FOUND,
    FILTER_PARAMETER_NOT_FOUND,
    INCORRECT_PASSWORD,
    MANDATORY_FIELD_NOT_DEFINED;

    public String code() {
        return name();
    }

}
