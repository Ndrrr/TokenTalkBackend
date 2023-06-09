package com.tokentalk.post.service;

import com.tokentalk.post.client.UserProfileClient;
import com.tokentalk.post.client.UserProfileFilter;
import com.tokentalk.post.domain.Post;
import com.tokentalk.post.dto.FileType;
import com.tokentalk.post.dto.PostDto;
import com.tokentalk.post.dto.PostFilter;
import com.tokentalk.post.dto.request.CreatePostRequest;
import com.tokentalk.post.dto.request.DeletePostRequest;
import com.tokentalk.post.dto.response.PostResponse;
import com.tokentalk.post.error.BaseException;
import com.tokentalk.post.error.ErrorCode;
import com.tokentalk.post.mapper.PostMapper;
import com.tokentalk.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final static List<String> allowedFileExtensions =
            List.of("jpg", "png", "jpeg", "gif", "mp4", "mov", "mp3", "wav");

    private final PostRepository postRepository;
    private final FileService fileService;
    private final UserProfileClient userProfileClient;
    private final PostMapper postMapper;
    private final Tika tika;

    public String create(String authorEmailFromJwt, CreatePostRequest request) {
        log.info("authorEmailFromJwt: " + authorEmailFromJwt);
        if (!Objects.equals(authorEmailFromJwt, request.getAuthorEmail())) {
            throw BaseException.of(ErrorCode.INVALID_AUTHOR_EMAIL, "Author email is not valid");
        }
        String fileId = saveFile(request);

        Post post = postMapper.toPost(request, fileId);
        Post created = postRepository.save(post);
        log.info("Post created: {}", post);
        return created.getId();
    }

    private String saveFile(CreatePostRequest request) {
        if (request.getFile() == null) {
            return null;
        }
        validateFileExtension(request.getFile());
        String mimeType = detectMimeType(request.getFile());
        request.setMimeType(mimeType);
        if (mimeType.startsWith("image")) {
            request.setFileType(FileType.IMAGE);
            return fileService.saveFile(request.getFile());
        } else if (mimeType.startsWith("video")) {
            request.setFileType(FileType.VIDEO);
            return fileService.saveFile(request.getFile());
        } else if (mimeType.startsWith("audio")) {
            request.setFileType(FileType.AUDIO);
            return fileService.saveFile(request.getFile());
        }
        throw BaseException.of(ErrorCode.INVALID_FILE_TYPE, "File type is not valid");
    }

    private String detectMimeType(MultipartFile file) {
        try {
            return tika.detect(file.getInputStream());
        } catch (IOException e) {
            throw BaseException.of(ErrorCode.FILE_READ_ERROR, "File read error");
        }
    }

    private void validateFileExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension == null || !allowedFileExtensions.contains(extension.toLowerCase())) {
            throw BaseException.of(
                    ErrorCode.INVALID_FILE_EXTENSION, "File extension is not valid");
        }
    }

    public PostResponse getAll(PostFilter filter) {
        List<Post> posts;
        if (!CollectionUtils.isEmpty(filter.getAuthorEmails())) {
            posts = postRepository.findAllByAuthorEmailIn(filter.getAuthorEmails());
        } else {
            posts = postRepository.findAll();
        }
        var postDtos = posts.stream()
                .map(post -> {
                    var userProfile = userProfileClient.getProfile(
                            UserProfileFilter.withEmail(post.getAuthorEmail()));
                    return postMapper.toPostDto(post, userProfile);
                })
                .toList();
        log.info("Post list: {}", postDtos);
        return PostResponse.of(postDtos);
    }

    public PostDto getById(String id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> BaseException.of(ErrorCode.POST_NOT_FOUND, "Post not found"));
        var userProfile =
                userProfileClient.getProfile(UserProfileFilter.withEmail(post.getAuthorEmail()));

        return postMapper.toPostDto(post, userProfile);
    }

    public void deleteById(String authorEmail, DeletePostRequest request) {
        log.info("authorEmailFromJwt: " + authorEmail);
        if (!Objects.equals(authorEmail, request.getAuthorEmail())) {
            throw BaseException.of(ErrorCode.INVALID_AUTHOR_EMAIL, "Author email is not valid");
        }

        var post = postRepository.findById(request.getId());
        if (post.isPresent()) {
            fileService.deleteFile(post.get().getFileId());
            postRepository.deleteById(request.getId());
        }
    }

}
