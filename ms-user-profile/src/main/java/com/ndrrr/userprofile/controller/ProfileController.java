package com.ndrrr.userprofile.controller;

import com.ndrrr.userprofile.dto.UserProfileDto;
import com.ndrrr.userprofile.dto.UserProfileFilter;
import com.ndrrr.userprofile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

}
