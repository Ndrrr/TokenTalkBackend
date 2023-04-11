package com.tokentalk.auth.dto;

import lombok.Data;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private String profileImage;

}
