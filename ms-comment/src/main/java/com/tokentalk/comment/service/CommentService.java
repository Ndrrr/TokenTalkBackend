package com.tokentalk.comment.service;

import com.tokentalk.comment.domain.Post;
import com.tokentalk.comment.dto.request.MakeCommentRequest;
import com.tokentalk.comment.error.BaseException;
import com.tokentalk.comment.mapper.CommentMapper;
import com.tokentalk.comment.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.tokentalk.comment.error.ErrorCode.INVALID_AUTHOR_EMAIL;
import static com.tokentalk.comment.error.ErrorCode.POST_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    public void makeComment(String authorEmail, MakeCommentRequest request) {
        log.info("makeComment: authorEmail={}, request={}", authorEmail, request);
        if (!Objects.equals(authorEmail, request.getAuthorEmail())) {
            throw BaseException.of(INVALID_AUTHOR_EMAIL, "Invalid author email");
        }
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> BaseException.of(POST_NOT_FOUND, "Post not found"));

        post.getComments().add(commentMapper.toComment(request));
        postRepository.save(post);
    }

}
