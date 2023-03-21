package com.example.apigateway.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Arrays;
import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorAttributes.class);

    public GlobalErrorAttributes() {
    }

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request,
                                                  ErrorAttributeOptions options) {
        Throwable error = this.getError(request);
        logger.error("Error occured", error);
        Map<String, Object> map = super.getErrorAttributes(request, options);
        String errorCode = getErrorCode(error);
        map.put("errorCode", errorCode);
        map.put("status", getStatus(error));
        map.remove("error");
        return map;
    }

    private String getErrorCode(Throwable error) {
        if (error instanceof BaseException)
            return ((BaseException) error).getCode();
        return "UNEXPECTED_ERROR";
    }

    private int getStatus(Throwable error) {
        if (error instanceof BaseException) {
            return Arrays.stream(ErrorCode.values())
                    .filter(e -> e.code().equals(((BaseException) error).getCode()))
                    .findFirst()
                    .map(ErrorCode::status)
                    .orElse(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

}
