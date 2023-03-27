package com.tokentalk.post.client.response;

import lombok.Data;

@Data
public class UserProfileResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String profileImage;

}
