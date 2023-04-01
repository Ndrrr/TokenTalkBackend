package com.tokentalk.post.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.tokentalk.post.domain.PostFile;
import com.tokentalk.post.error.BaseException;
import com.tokentalk.post.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileService {

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;

    public String saveFile(MultipartFile file) {
        DBObject metaData = new BasicDBObject();
        try {
            ObjectId id = gridFsTemplate.store(
                    file.getInputStream(), file.getName(), file.getContentType(), metaData);
            return id.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PostFile getFile(String id) {
        GridFSFile video = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (video == null) {
            throw BaseException.of(ErrorCode.POST_FILE_NOT_FOUND, "File not found");
        }
        GridFsResource resource = operations.getResource(video);

        return PostFile.of(video, resource);
    }

}