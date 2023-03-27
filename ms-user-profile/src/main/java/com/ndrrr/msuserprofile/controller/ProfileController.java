package com.ndrrr.msuserprofile.controller;

import com.ndrrr.msuserprofile.dto.UserProfileDto;
import com.ndrrr.msuserprofile.dto.UserProfileFilter;
import com.ndrrr.msuserprofile.service.ProfileService;
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
