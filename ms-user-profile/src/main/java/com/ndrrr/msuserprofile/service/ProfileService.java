package com.ndrrr.msuserprofile.service;

import com.ndrrr.msuserprofile.dto.UserProfileDto;
import com.ndrrr.msuserprofile.dto.UserProfileFilter;
import com.ndrrr.msuserprofile.error.BaseException;
import com.ndrrr.msuserprofile.error.ErrorCode;
import com.ndrrr.msuserprofile.mapper.ProfileMapper;
import com.ndrrr.msuserprofile.repository.ProfileRepository;
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

    private UserProfileDto getProfileByEmail(String email) {
        var profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> BaseException.of(
                        ErrorCode.PROFILE_NOT_FOUND,
                        "Profile not found for email: " + email)
                );
        return profileMapper.toUserProfileDto(profile);
    }

    private UserProfileDto getProfileById(Long id) {
        var profile = profileRepository.findById(id)
                .orElseThrow(() -> BaseException.of(
                        ErrorCode.PROFILE_NOT_FOUND,
                        "Profile not found for id: " + id)
                );
        log.info("Profile found: {}", profile);
        return profileMapper.toUserProfileDto(profile);
    }

}
