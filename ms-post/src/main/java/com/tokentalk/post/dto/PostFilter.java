package com.tokentalk.post.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostFilter {

    private List<Long> authorIds;

}
