package com.example.apigateway.filter;

import com.example.apigateway.error.BaseException;
import com.example.apigateway.error.ErrorCode;
import com.example.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private JwtUtil jwtUtil;

    public AuthenticationFilter(RouteValidator validator) {
        super(Config.class);
        this.validator = validator;
    }

    @Autowired
    public void setJwtUtil(JwtUtil util) {
        this.jwtUtil = util;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                String authHeader = getAuthorizationHeader(exchange.getRequest());
                String token = getTokenFromHeader(authHeader);
                try {
                    setSubjectToHeader(exchange, jwtUtil.validateToken(token));
                } catch (Exception e) {
                    throw BaseException.of(ErrorCode.INVALID_TOKEN, "Invalid token");
                }
            }
            return chain.filter(exchange);
        });
    }

    private void setSubjectToHeader(ServerWebExchange exchange, String token) {
        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("userEmail", token)
                .build();

        exchange.mutate()
                .request(request)
                .build();
    }

    private String getAuthorizationHeader(ServerHttpRequest request) {
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw BaseException.of(
                    ErrorCode.MISSING_AUTHORIZATION_HEADER, "Missing authorization header");
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
