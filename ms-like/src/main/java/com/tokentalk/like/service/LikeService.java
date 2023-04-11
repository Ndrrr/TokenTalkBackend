package com.tokentalk.like.service;

import com.tokentalk.like.domain.Post;
import com.tokentalk.like.error.BaseException;
import com.tokentalk.like.repository.PostRepository;
import com.tokentalk.like.request.LikeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.tokentalk.like.error.ErrorCode.INVALID_AUTHOR_EMAIL;
import static com.tokentalk.like.error.ErrorCode.POST_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;

    public void like(String authorEmail, LikeRequest request) {
        log.info("makeComment: authorEmail={}, request={}", authorEmail, request);
        if (!Objects.equals(authorEmail, request.getAuthorEmail())) {
            throw BaseException.of(INVALID_AUTHOR_EMAIL, "Invalid author email");
        }
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> BaseException.of(POST_NOT_FOUND, "Post not found"));

        if(post.getLikes().contains(request.getAuthorEmail()))
            post.getLikes().remove(request.getAuthorEmail());
        else
            post.getLikes().add(request.getAuthorEmail());
        postRepository.save(post);
    }

}
