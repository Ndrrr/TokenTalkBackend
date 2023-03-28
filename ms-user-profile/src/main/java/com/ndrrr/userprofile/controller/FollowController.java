package com.ndrrr.userprofile.controller;

import com.ndrrr.userprofile.dto.request.FollowRequest;
import com.ndrrr.userprofile.dto.response.FolloweesResponse;
import com.ndrrr.userprofile.dto.response.FollowersResponse;
import com.ndrrr.userprofile.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public void follow(@RequestBody FollowRequest request) {
        followService.follow(request);
    }

    @GetMapping("/followers/{email}")
    public FollowersResponse getFollowers(@PathVariable String email) {
        return followService.getFollowers(email);
    }

    @GetMapping("/followees/{email}")
    public FolloweesResponse getFollowees(@PathVariable String email) {
        return followService.getFollowees(email);
    }

}
