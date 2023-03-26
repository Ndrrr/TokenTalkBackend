package com.tokentalk.post.service;

import com.tokentalk.post.domain.ImageFile;
import com.tokentalk.post.error.BaseException;
import com.tokentalk.post.error.ErrorCode;
import com.tokentalk.post.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String saveImage(MultipartFile file) {
        var image = new ImageFile();
        try {
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageRepository.insert(image).getId();
    }

    public ImageFile getImage(String id) {
        return imageRepository.findById(id)
                .orElseThrow(() ->
                        BaseException.of(ErrorCode.POST_FILE_NOT_FOUND, "Image not found"));
    }

}
