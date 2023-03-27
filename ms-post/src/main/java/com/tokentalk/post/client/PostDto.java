package com.tokentalk.post.client;

import lombok.Data;

@Data
public class PostDto {

    private String content;
    private Long authorId;

}