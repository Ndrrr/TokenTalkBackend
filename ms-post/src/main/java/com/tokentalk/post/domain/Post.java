package com.tokentalk.post.domain;

import com.tokentalk.post.dto.FileType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String content;
    private String authorEmail;
    private String fileId;
    private FileType fileType;
    private String mimeType;
    private LocalDateTime createdAt;

}
