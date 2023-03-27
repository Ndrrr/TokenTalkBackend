package com.ndrrr.msuserprofile.mapper;

import com.ndrrr.msuserprofile.domain.UserProfile;
import com.ndrrr.msuserprofile.dto.UserProfileDto;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    UserProfileDto toUserProfileDto(UserProfile userProfile);

}
