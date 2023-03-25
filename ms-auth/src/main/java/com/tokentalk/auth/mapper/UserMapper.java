package com.tokentalk.auth.mapper;

import com.tokentalk.auth.domain.User;
import com.tokentalk.auth.dto.request.RegisterRequest;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public abstract class UserMapper {

    public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    private PasswordEncoder encoder;

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    public abstract User toUser(RegisterRequest request);

    @Named("encodePassword")
    public String encodePassword(String password) {
        return encoder.encode(password);
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

}
