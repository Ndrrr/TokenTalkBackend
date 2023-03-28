package com.ndrrr.userprofile.dto.request;

import lombok.Data;

@Data
public class FollowRequest {

    private String followeeEmail;
    private String followerEmail;
}
