package com.tokentalk.post.controller;

import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.PostFilter;
import com.tokentalk.post.dto.request.CreatePostRequest;
import com.tokentalk.post.dto.response.PostResponse;
import com.tokentalk.post.service.PostService;
import com.tokentalk.post.service.VideoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final VideoService videoService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@ModelAttribute @Valid CreatePostRequest request) {
        return postService.create(request);
    }

    @GetMapping("/all")
    public PostResponse getAll(PostFilter filter) {
        return postService.getAll(filter);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable("id") String id) {
        return postService.getById(id);
    }

    @GetMapping(value = "/videos/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void streamVideo(@PathVariable String id, HttpServletResponse response) throws IOException {
        var video = videoService.getVideo(id);
        FileCopyUtils.copy(video.getStream(), response.getOutputStream());
    }

}
