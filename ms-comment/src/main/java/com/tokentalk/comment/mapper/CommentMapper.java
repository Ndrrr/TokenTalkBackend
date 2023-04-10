package com.tokentalk.comment.mapper;

import com.tokentalk.comment.domain.Comment;
import com.tokentalk.comment.dto.request.MakeCommentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", source = "request", qualifiedByName = "generateId")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Comment toComment(MakeCommentRequest request);

    @Named("generateId")
    default String generateId(MakeCommentRequest request) {
        return java.util.UUID.randomUUID().toString();
    }

}
