package com.tokentalk.like.request;

import lombok.Data;

@Data
public class LikeRequest {

    private String postId;
    private String authorEmail;

}
