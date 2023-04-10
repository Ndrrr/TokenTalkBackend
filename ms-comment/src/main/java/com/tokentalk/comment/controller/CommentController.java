package com.tokentalk.comment.controller;

import com.tokentalk.comment.dto.request.MakeCommentRequest;
import com.tokentalk.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public void makeComment(@RequestHeader("userEmail") String authorEmail,
                            @RequestBody MakeCommentRequest request) {
        commentService.makeComment(authorEmail, request);
    }

}
