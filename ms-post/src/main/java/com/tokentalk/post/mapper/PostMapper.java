package com.tokentalk.post.mapper;

import com.tokentalk.post.client.response.UserProfileResponse;
import com.tokentalk.post.domain.Post;
import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.request.CreatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
            uses = UserProfileMapper.class)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "content", source = "request.content")
    @Mapping(target = "authorEmail", source = "request.authorEmail")
    @Mapping(target = "fileType", source = "request.fileType")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Post toPost(CreatePostRequest request, String fileId);

    @Mapping(target = "id", source = "post.id")
    @Mapping(target = "fileType", source = "post.fileType")
    @Mapping(target = "fileId", source = "post.fileId")
    @Mapping(target =" content", source = "post.content")
    @Mapping(target = "createdAt", source = "post.createdAt")
    @Mapping(target = "comments", source = "post.comments")
    @Mapping(target = "likes", source = "post.likes")
    @Mapping(target = "user", source = "userProfile", qualifiedByName = "toUserProfileDto")
    PostDto toPostDto(Post post, UserProfileResponse userProfile);

}
