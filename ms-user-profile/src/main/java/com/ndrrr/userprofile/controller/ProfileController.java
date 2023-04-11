package com.ndrrr.userprofile.controller;

import com.ndrrr.userprofile.dto.UserProfileDto;
import com.ndrrr.userprofile.dto.UserProfileFilter;
import com.ndrrr.userprofile.dto.request.UpdateProfileRequest;
import com.ndrrr.userprofile.dto.response.SearchUserResponse;
import com.ndrrr.userprofile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public UserProfileDto getProfile(UserProfileFilter filter) {
        return profileService.getProfile(filter);
    }

    @GetMapping("/search")
    public SearchUserResponse search(UserProfileFilter filter) {
        return profileService.searchUser(filter);
    }

    @PostMapping("/update")
    public UserProfileDto update(@RequestHeader("userEmail") String authorEmail,
                                 @RequestBody UpdateProfileRequest request) {
        return profileService.update(authorEmail, request);
    }

}
