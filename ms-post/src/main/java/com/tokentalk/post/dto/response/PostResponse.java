package com.tokentalk.post.dto.response;

import com.tokentalk.post.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class PostResponse {

    private List<PostDto> posts;

}
