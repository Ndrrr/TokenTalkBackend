package com.tokentalk.post.dto.request;

import com.tokentalk.post.dto.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class CreatePostRequest {

    @NotBlank
    private String content;

    @NotNull
    private Long authorId;

    private MultipartFile file;

    private FileType fileType;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

}