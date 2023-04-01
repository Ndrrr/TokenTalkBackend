package com.tokentalk.post.controller;

import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.PostFilter;
import com.tokentalk.post.dto.request.CreatePostRequest;
import com.tokentalk.post.dto.response.PostResponse;
import com.tokentalk.post.service.FileService;
import com.tokentalk.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestHeader("userEmail") String authorEmail, @ModelAttribute @Valid CreatePostRequest request) {
        return postService.create(authorEmail, request);
    }

    @GetMapping("/all")
    public PostResponse getAll(PostFilter filter) {
        return postService.getAll(filter);
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable("id") String id) {
        return postService.getById(id);
    }

    @GetMapping(value = "/files/{id}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String id) throws IOException {
        var file = fileService.getFile(id);
        return ResponseEntity.ok()
                .contentLength(file.getFile().getLength())
                .contentType(MediaType.parseMediaType(file.getResource().getContentType()))
                .body(new InputStreamResource(file.getResource().getInputStream()));
    }

}
