package com.tokentalk.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private String content;
    private Long authorId;
    private LocalDateTime createdAt;

}
