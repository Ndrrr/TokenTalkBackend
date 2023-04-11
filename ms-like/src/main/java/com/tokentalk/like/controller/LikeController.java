package com.tokentalk.like.controller;

import com.tokentalk.like.request.LikeRequest;
import com.tokentalk.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public void makeComment(@RequestHeader("userEmail") String authorEmail,
                            @RequestBody LikeRequest request) {
        likeService.like(authorEmail, request);
    }

}
