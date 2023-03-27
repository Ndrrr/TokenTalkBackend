package com.tokentalk.post.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {

    private String content;
    private LocalDateTime createdAt;
    private FileType fileType;
    private String file;
    private UserProfileDto user;

}
