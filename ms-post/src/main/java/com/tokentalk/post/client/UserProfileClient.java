package com.tokentalk.post.client;

import com.tokentalk.post.client.response.UserProfileResponse;
import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.jackson.JacksonDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "ms-user-profile",
        contextId = "ms-user-profile",
        configuration = UserProfileClient.FeignConfiguration.class)
public interface UserProfileClient {

    @GetMapping("/api/profile")
    UserProfileResponse getProfile(@SpringQueryMap UserProfileFilter filter);

    class FeignConfiguration {

        @Bean
        public ErrorDecoder feignErrorDecoder() {
            return AnnotationErrorDecoder.builderFor(UserProfileClient.class)
                    .withResponseBodyDecoder(new JacksonDecoder()).build();
        }

    }

}