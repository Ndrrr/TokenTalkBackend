package com.ndrrr.userprofile.service;

import com.ndrrr.userprofile.domain.UserProfile;
import com.ndrrr.userprofile.dto.UserProfileDto;
import com.ndrrr.userprofile.dto.UserProfileFilter;
import com.ndrrr.userprofile.error.BaseException;
import com.ndrrr.userprofile.error.ErrorCode;
import com.ndrrr.userprofile.mapper.ProfileMapper;
import com.ndrrr.userprofile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public UserProfileDto getProfile(UserProfileFilter filter) {
        if(filter.getEmail() != null) {
            return getProfileByEmail(filter.getEmail());
        }
        if(filter.getId() != null) {
            return getProfileById(filter.getId());
        }
        throw BaseException.of(ErrorCode.FILTER_PARAMETER_NOT_FOUND, "Invalid filter");
    }

    public UserProfileDto getProfileByEmail(String email) {
        var profile = getProfileByEmailOrThrow(email);
        return profileMapper.toUserProfileDto(profile);
    }

    public UserProfile getProfileByEmailOrThrow(String email) {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> BaseException.of(
                        ErrorCode.PROFILE_NOT_FOUND,
                        "Profile not found for email: " + email)
                );
    }

    public UserProfileDto getProfileById(Long id) {
        var profile = profileRepository.findById(id)
                .orElseThrow(() -> BaseException.of(
                        ErrorCode.PROFILE_NOT_FOUND,
                        "Profile not found for id: " + id)
                );
        log.info("Profile found: {}", profile);
        return profileMapper.toUserProfileDto(profile);
    }

}
