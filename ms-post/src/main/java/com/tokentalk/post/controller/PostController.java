package com.tokentalk.post.controller;

import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.request.CreatePostRequest;
import com.tokentalk.post.dto.response.PostResponse;
import com.tokentalk.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreatePostRequest request) {
        postService.create(request);
    }

    @GetMapping("/all")
    public PostResponse getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable("id") String id) {
        return postService.getById(id);
    }

}
