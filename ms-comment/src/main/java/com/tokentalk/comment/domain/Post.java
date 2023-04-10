package com.tokentalk.comment.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Set<Comment> comments;

    public Set<Comment> getComments() {
        if (comments == null) {
            comments = new java.util.HashSet<>();
        }
        return comments;
    }

}
