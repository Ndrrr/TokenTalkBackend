package com.tokentalk.post.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.tokentalk.post.domain.VideoFile;
import com.tokentalk.post.error.BaseException;
import com.tokentalk.post.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final GridFsTemplate gridFsTemplate;
    private final GridFsOperations operations;

    public String saveVideo(MultipartFile file) {
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "video");
        try {
            ObjectId id = gridFsTemplate.store(
                    file.getInputStream(), file.getName(), file.getContentType(), metaData);
            return id.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public VideoFile getVideo(String id) {
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
        if (file == null) {
            throw BaseException.of(ErrorCode.POST_FILE_NOT_FOUND, "Video not found");
        }
        VideoFile video = new VideoFile();
        try {
            video.setStream(operations.getResource(file).getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return video;
    }

}