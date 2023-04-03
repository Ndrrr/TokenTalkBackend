package com.tokentalk.post.dto.request;

import com.tokentalk.post.dto.FileType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreatePostRequest {

    @NotBlank
    private String content;

    @NotNull
    private String authorEmail;

    private MultipartFile file;

    private FileType fileType;
    private String mimeType;

}