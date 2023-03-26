package com.tokentalk.post.mapper;

import com.tokentalk.post.domain.Post;
import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.request.CreatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "content", source = "request.content")
    @Mapping(target = "authorId", source = "request.authorId")
    @Mapping(target = "fileType", source = "request.fileType")
    @Mapping(target = "createdAt", source = "request.createdAt")
    Post toPost(CreatePostRequest request, String fileId);

    @Mapping(target = "fileType", source = "post.fileType")
    @Mapping(target =" content", source = "post.content")
    @Mapping(target = "authorId", source = "post.authorId")
    @Mapping(target = "createdAt", source = "post.createdAt")
    PostDto toPostDto(Post post, String file);

}
