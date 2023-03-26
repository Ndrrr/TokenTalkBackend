package com.tokentalk.post.service;

import com.tokentalk.post.domain.Post;
import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.PostFilter;
import com.tokentalk.post.dto.request.CreatePostRequest;
import com.tokentalk.post.dto.response.PostResponse;
import com.tokentalk.post.error.BaseException;
import com.tokentalk.post.error.ErrorCode;
import com.tokentalk.post.mapper.PostMapper;
import com.tokentalk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public void create(Long realAuthorId, CreatePostRequest request) {
        if (!Objects.equals(realAuthorId, request.getAuthorId())) {
            throw BaseException.of(ErrorCode.INVALID_AUTHOR_ID, "Author id is not valid");
        }
        Post post = postMapper.toPost(request);
        postRepository.save(post);
        log.info("Post created: {}", post);
    }

    public PostResponse getAll(PostFilter filter) {
        List<Post> posts;
        if (!CollectionUtils.isEmpty(filter.getAuthorIds())) {
            posts = postRepository.findAllByAuthorIdIn(filter.getAuthorIds());
        } else {
            posts = postRepository.findAll();
        }
        return PostResponse.of(postMapper.toPostDtoList(posts));
    }

    public PostDto getById(String id) {
        var post = postRepository.findById(id).orElseThrow();
        return postMapper.toPostDto(post);
    }

}
