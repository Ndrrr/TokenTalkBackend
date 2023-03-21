package com.example.apigateway.client;

import com.example.apigateway.error.BaseException;
import com.example.apigateway.error.ErrorCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler 
  implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
      throws IOException {
        return httpResponse.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) 
      throws IOException {
        if(httpResponse.getStatusCode().is4xxClientError()) {
            throw BaseException.of(ErrorCode.INVALID_TOKEN, "Invalid token");
        }
        throw new RuntimeException("Unknown error");
    }
}