package com.tokentalk.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tokentalk.post.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

    private String id;
    private String content;
    private LocalDateTime createdAt;
    private FileType fileType;
    private String fileId;
    private String mimeType;
    private UserProfileDto user;
    private Set<Comment> comments;

}
