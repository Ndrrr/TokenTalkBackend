package com.tokentalk.post.repository;

import com.tokentalk.post.domain.ImageFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageFile, String> {
}
