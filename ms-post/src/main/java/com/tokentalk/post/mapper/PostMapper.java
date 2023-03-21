package com.tokentalk.post.mapper;

import com.tokentalk.post.domain.Post;
import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.request.CreatePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post toPost(CreatePostRequest createPostRequest);

    PostDto toPostDto(Post post);

    List<PostDto> toPostDtoList(List<Post> posts);
}
