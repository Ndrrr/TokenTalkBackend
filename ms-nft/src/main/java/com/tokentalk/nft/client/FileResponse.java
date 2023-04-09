package com.tokentalk.nft.client;

import lombok.Data;
import org.springframework.core.io.InputStreamResource;

@Data
public class FileResponse {

    private InputStreamResource file;
}
