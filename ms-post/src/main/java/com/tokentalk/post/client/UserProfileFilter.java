package com.tokentalk.post.client;

import lombok.Data;

@Data
public class UserProfileFilter {

    private String email;
    private Long id;

    public static UserProfileFilter withEmail(String email) {
        UserProfileFilter filter = new UserProfileFilter();
        filter.setEmail(email);
        return filter;
    }

}
