package com.tokentalk.nft.client;

import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.jackson.JacksonDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "ms-post",
        contextId = "ms-post",
        configuration = PostClient.FeignConfiguration.class)
public interface PostClient {

    @GetMapping("/api/posts/file/{id}")
    FileResponse getFile(@PathVariable("id") String id);

    class FeignConfiguration {

        @Bean
        public ErrorDecoder feignErrorDecoder() {
            return AnnotationErrorDecoder.builderFor(PostClient.class)
                    .withResponseBodyDecoder(new JacksonDecoder()).build();
        }

    }

}
