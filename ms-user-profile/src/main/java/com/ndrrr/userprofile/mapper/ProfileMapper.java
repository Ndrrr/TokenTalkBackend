package com.ndrrr.userprofile.mapper;

import com.ndrrr.userprofile.domain.UserProfile;
import com.ndrrr.userprofile.dto.UserProfileDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    UserProfileDto toUserProfileDto(UserProfile userProfile);

    default List<UserProfileDto> toFollowers(UserProfile user) {
        return user.getFollowers().stream().map(this::toUserProfileDto).toList();
    }

    default List<UserProfileDto> toFollowees(UserProfile user) {
        return user.getFollowees().stream().map(this::toUserProfileDto).toList();
    }

}
