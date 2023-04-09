package com.tokentalk.nft.controller;

import com.tokentalk.nft.dto.request.UploadToInfuraRequest;
import com.tokentalk.nft.dto.response.UploadToInfuraResponse;
import com.tokentalk.nft.service.NftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nft")
@RequiredArgsConstructor
public class NftController {

    private final NftService nftService;

    @PostMapping("/uploadToInfura")
    public UploadToInfuraResponse uploadToInfura(@RequestBody UploadToInfuraRequest request) {
        return nftService.uploadToInfura(request);
    }

}
