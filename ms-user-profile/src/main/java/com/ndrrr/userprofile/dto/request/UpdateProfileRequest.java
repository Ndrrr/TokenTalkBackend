package com.ndrrr.userprofile.dto.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String password;
    private String description;
    private String newPassword;

}
