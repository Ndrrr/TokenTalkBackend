package com.tokentalk.comment.config;

import com.tokentalk.comment.mapper.CommentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CommentMapper commentMapper() {
        return CommentMapper.INSTANCE;
    }
}
