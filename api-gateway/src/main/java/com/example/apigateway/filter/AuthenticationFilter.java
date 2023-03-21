package com.example.apigateway.filter;

import com.example.apigateway.client.AuthClient;
import com.example.apigateway.client.request.ValidateTokenRequest;
import com.example.apigateway.client.response.ValidateTokenResponse;
import com.example.apigateway.error.BaseException;
import com.example.apigateway.error.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private AuthClient client;

    public AuthenticationFilter(RouteValidator validator) {
        super(Config.class);
        this.validator = validator;
    }

    @Autowired
    public void setAuthClient(AuthClient client) {
        this.client = client;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                String authHeader = getAuthorizationHeader(exchange.getRequest());
                String token = getTokenFromHeader(authHeader);
                var response = client.validateToken(ValidateTokenRequest.of(token));
                setSubjectToHeader(exchange, response);
            }
            return chain.filter(exchange);
        });
    }

    private void setSubjectToHeader(ServerWebExchange exchange,
                                    ResponseEntity<ValidateTokenResponse> response) {
        if (Objects.isNull(response.getBody()) || Objects.isNull(response.getBody().getUserId()))
            throw BaseException.of(ErrorCode.INVALID_SUBJECT, "Invalid subject");

        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("userId", response.getBody().getUserId().toString())
                .build();

        exchange.mutate()
                .request(request)
                .build();
    }

    private String getAuthorizationHeader(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw BaseException.of(
                    ErrorCode.MISSING_AUTHORIZATION_HEADER,"Missing authorization header");
        }
        return request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
    }

    private String getTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public static class Config {
        // Put the configuration properties
    }
}
