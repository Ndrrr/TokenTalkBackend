package com.tokentalk.post.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "files")
public abstract class BaseFile {

    @Id
    private String id;

}
