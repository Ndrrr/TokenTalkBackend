package com.tokentalk.post.service;

import com.tokentalk.post.domain.Post;
import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.request.CreatePostRequest;
import com.tokentalk.post.dto.response.PostResponse;
import com.tokentalk.post.mapper.PostMapper;
import com.tokentalk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public void create(CreatePostRequest request) {
        Post post = postMapper.toPost(request);
        postRepository.save(post);
        log.info("Post created: {}", post);
    }

    public PostResponse getAll() {
        var posts = postRepository.findAll();
        return PostResponse.of(postMapper.toPostDtoList(posts));
    }

    public PostDto getById(String id) {
        var post = postRepository.findById(id).orElseThrow();
        return postMapper.toPostDto(post);
    }

}
