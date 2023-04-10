package com.tokentalk.comment.dto.request;

import lombok.Data;

@Data
public class MakeCommentRequest {

    private String postId;
    private String authorEmail;
    private String authorName;
    private String content;

}
