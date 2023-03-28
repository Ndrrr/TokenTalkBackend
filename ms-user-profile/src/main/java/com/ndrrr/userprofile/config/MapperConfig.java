package com.ndrrr.userprofile.config;

import com.ndrrr.userprofile.mapper.ProfileMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ProfileMapper postMapper() {
        return ProfileMapper.INSTANCE;
    }

}
