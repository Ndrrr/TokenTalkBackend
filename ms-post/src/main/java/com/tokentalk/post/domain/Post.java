package com.tokentalk.post.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String content;
    private Long authorId;

}
