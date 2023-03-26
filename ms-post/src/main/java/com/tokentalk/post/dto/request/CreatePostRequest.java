package com.tokentalk.post.dto.request;

import lombok.Data;

@Data
public class CreatePostRequest {

    private String content;
    private Long authorId;

}