package com.tokentalk.post.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    private String id;
    private String authorEmail;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;

}
