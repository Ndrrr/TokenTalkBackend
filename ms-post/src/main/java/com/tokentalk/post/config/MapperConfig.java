package com.tokentalk.post.config;

import com.tokentalk.post.mapper.PostMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public PostMapper postMapper() {
        return PostMapper.INSTANCE;
    }

}
