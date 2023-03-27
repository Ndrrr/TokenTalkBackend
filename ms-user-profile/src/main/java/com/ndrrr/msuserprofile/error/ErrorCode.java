package com.ndrrr.msuserprofile.error;

public enum ErrorCode {

    PROFILE_NOT_FOUND,
    FILTER_PARAMETER_NOT_FOUND,
    MANDATORY_FIELD_NOT_DEFINED;

    public String code() {
        return name();
    }

}
