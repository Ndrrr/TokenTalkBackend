package com.tokentalk.post.service;

import com.tokentalk.post.client.UserProfileClient;
import com.tokentalk.post.client.UserProfileFilter;
import com.tokentalk.post.domain.Post;
import com.tokentalk.post.dto.FileType;
import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.PostFilter;
import com.tokentalk.post.dto.request.CreatePostRequest;
import com.tokentalk.post.dto.response.PostResponse;
import com.tokentalk.post.error.BaseException;
import com.tokentalk.post.error.ErrorCode;
import com.tokentalk.post.mapper.PostMapper;
import com.tokentalk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;
    private final VideoService videoService;
    private final UserProfileClient userProfileClient;
    private final PostMapper postMapper;

    public void create(CreatePostRequest request) {
//        if (!Objects.equals(realAuthorId, request.getAuthorId())) {
//            throw BaseException.of(ErrorCode.INVALID_AUTHOR_ID, "Author id is not valid");
//        }
        String fileId = saveFile(request);

        Post post = postMapper.toPost(request, fileId);
        postRepository.save(post);
        log.info("Post created: {}", post);
    }

    private String saveFile(CreatePostRequest request) {
        if (request.getFile() == null) {
            return null;
        }
        if (request.getFileType() == FileType.IMAGE) {
            return imageService.saveImage(request.getFile());
        } else if (request.getFileType() == FileType.VIDEO) {
            return videoService.saveVideo(request.getFile());
        }
        return null;
    }

    public PostResponse getAll(PostFilter filter) {
        List<Post> posts;
        if (!CollectionUtils.isEmpty(filter.getAuthorIds())) {
            posts = postRepository.findAllByAuthorIdIn(filter.getAuthorIds());
        } else {
            posts = postRepository.findAll();
        }
        var postDtos = posts.stream()
                .map(post -> {
                    var userProfile =
                            userProfileClient.getProfile(UserProfileFilter.withId(post.getAuthorId()));

                    return postMapper.toPostDto(post, userProfile, getFile(post));
                })
                .toList();
        return PostResponse.of(postDtos);
    }

    public PostDto getById(String id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> BaseException.of(ErrorCode.POST_NOT_FOUND, "Post not found"));
        var userProfile =
                userProfileClient.getProfile(UserProfileFilter.withId(post.getAuthorId()));

        return postMapper.toPostDto(post, userProfile, getFile(post));
    }

    private String getFile(Post post) {
        if (post.getFileType() == FileType.IMAGE) {
            return Base64.getEncoder()
                    .encodeToString(
                            imageService.getImage(post.getFileId())
                                    .getImage()
                                    .getData()
                    );
        } else if (post.getFileType() == FileType.VIDEO) {
            return post.getFileId();
        }
        return null;
    }

}
