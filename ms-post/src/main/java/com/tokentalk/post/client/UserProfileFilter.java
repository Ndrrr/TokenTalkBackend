package com.tokentalk.post.client;

import lombok.Data;

@Data
public class UserProfileFilter {

    private String email;
    private Long id;

    public static UserProfileFilter withId(Long id) {
        UserProfileFilter filter = new UserProfileFilter();
        filter.setId(id);
        return filter;
    }

}
