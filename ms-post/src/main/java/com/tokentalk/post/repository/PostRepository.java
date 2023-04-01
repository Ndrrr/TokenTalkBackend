package com.tokentalk.post.repository;

import com.tokentalk.post.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findAllByAuthorEmailIn(Collection<String> authorEmail);

}
