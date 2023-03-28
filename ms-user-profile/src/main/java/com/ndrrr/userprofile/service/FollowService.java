package com.ndrrr.userprofile.service;

import com.ndrrr.userprofile.dto.request.FollowRequest;
import com.ndrrr.userprofile.dto.response.FolloweesResponse;
import com.ndrrr.userprofile.dto.response.FollowersResponse;
import com.ndrrr.userprofile.mapper.ProfileMapper;
import com.ndrrr.userprofile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public FollowersResponse getFollowers(String email) {
        var user = profileService.getProfileByEmailOrThrow(email);

        return FollowersResponse.of(profileMapper.toFollowers(user));
    }

    public FolloweesResponse getFollowees(String email) {
        var user = profileService.getProfileByEmailOrThrow(email);

        return FolloweesResponse.of(profileMapper.toFollowees(user));
    }

    public void follow(FollowRequest request) {
        var follower = profileService.getProfileByEmailOrThrow(request.getFollowerEmail());
        var followee = profileService.getProfileByEmailOrThrow(request.getFolloweeEmail());
        follower.getFollowees().add(followee);
        followee.getFollowers().add(follower);
        profileRepository.save(follower);
        profileRepository.save(followee);
    }

}
