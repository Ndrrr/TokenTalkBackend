package com.tokentalk.post.domain;

import com.mongodb.client.gridfs.model.GridFSFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.gridfs.GridFsResource;

@Data
@AllArgsConstructor(staticName = "of")
public class PostFile {

    private GridFSFile file;
    private GridFsResource resource;

}
