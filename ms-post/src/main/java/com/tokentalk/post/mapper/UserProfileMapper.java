package com.tokentalk.post.mapper;

import com.tokentalk.post.client.response.UserProfileResponse;
import com.tokentalk.post.dto.UserProfileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {

    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    @Named("toUserProfileDto")
    UserProfileDto toUserProfileDto(UserProfileResponse response);

}
